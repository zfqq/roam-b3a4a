package com.rabbiter.market.service.member_management.member.impl;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.write.builder.ExcelWriterBuilder;
import com.alibaba.fastjson.JSONObject;
import com.rabbiter.market.common.exception.BusinessException;
import com.rabbiter.market.domain.member_management.member.Member;
import com.rabbiter.market.mapper.member_management.member.MemberMapper;
import com.rabbiter.market.qo.member_management.member.QueryMember;
import com.rabbiter.market.service.member_management.member.IMemberService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.rabbiter.market.vo.member.MemberVo;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import sun.misc.BASE64Decoder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class MemberServiceImpl extends ServiceImpl<MemberMapper, Member> implements IMemberService {
    @Override
    public Page<Member> queryPageByQo(QueryMember qo) {
        Page<Member> page = new Page<>(qo.getCurrentPage(), qo.getPageSize());
        QueryWrapper<Member> wrapper = new QueryWrapper<>();
        wrapper.like(StringUtils.hasText(qo.getPhone()), "phone", qo.getPhone());
        wrapper.like(StringUtils.hasText(qo.getName()), "name", qo.getName());
        wrapper.eq(StringUtils.hasText(qo.getState()), "state", qo.getState());
        wrapper.eq(StringUtils.hasText(qo.getCardnumber()), "cardnumber", qo.getCardnumber());
        super.page(page, wrapper);
        return page;
    }

    @Override
    public void delMember(Long id) {
        UpdateWrapper<Member> wrapper = new UpdateWrapper<Member>()
                .set("state", Member.STATE_BAN)
                .set("integral", 0L)
                .eq("id", id);
        super.update(wrapper);
    }

    @Override
    public void saveMember(Member member) {
        QueryWrapper<Member> wrapper = new QueryWrapper<Member>().eq("phone", member.getPhone()).eq("state", Member.STATE_NORMAL);
        Member one = super.getOne(wrapper);
        if (one != null) {
            throw new BusinessException("该用户已注册过");
        }
        member.setPassword(Member.DEFAULT_PWD);
        member.setState(Member.STATE_NORMAL);
        member.setIntegral(0L);
        JSONObject jsonObject = uploadImage(member.getImage());
        if ((Boolean) jsonObject.get("success")) {
            member.setImage(jsonObject.get("msg").toString());
        }
        super.save(member);
    }

    @Override
    public Member queryMemberById(Long id) {
        Member byId = super.getById(id);
        return super.getById(id);
    }

    @Override
    public void updateMember(Member member) {
        if (Member.STATE_NORMAL.equals(member.getState())) {
            QueryWrapper<Member> wrapper = new QueryWrapper<Member>().eq("phone", member.getPhone())
                    .ne("id", member.getId())
                    .eq("state", Member.STATE_NORMAL);
            Member one = super.getOne(wrapper);
            if (one != null) {
                throw new BusinessException("已被录入");
            }
        }
        super.updateById(member);
    }

    @Override
    public Member queryMemberByPhone(String phone) {
        QueryWrapper<Member> wrapper = new QueryWrapper<Member>().eq("phone", phone).eq("state", Member.STATE_NORMAL);
        Member one = super.getOne(wrapper);
        if (one == null) {
            throw new BusinessException("该会员不存在");
        }
        return one;
    }

    @Override
    public void reportExportExcel(QueryMember qo, HttpServletRequest request, HttpServletResponse response) {
        Page<Member> page = new Page<>(qo.getCurrentPage(), qo.getPageSize());
        QueryWrapper<Member> wrapper = new QueryWrapper<>();
        wrapper.like(StringUtils.hasText(qo.getPhone()), "phone", qo.getPhone());
        wrapper.like(StringUtils.hasText(qo.getName()), "name", qo.getName());
        wrapper.eq(StringUtils.hasText(qo.getState()), "state", qo.getState());
        wrapper.eq(StringUtils.hasText(qo.getCardnumber()), "cardnumber", qo.getCardnumber());
        super.page(page, wrapper);
        List<Member> memberVos = new ArrayList<>(page.getRecords());
        try {
             response.setContentType("application/vnd.ms-excel");
            response.setCharacterEncoding("UTF-8");
            response.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode("会员信息", "UTF-8"));
            EasyExcel.write(response.getOutputStream(), MemberVo.class).sheet().doWrite(memberVos);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    public JSONObject uploadImage(String base64Data) {
        JSONObject jsonObject = new JSONObject();
        String dataPrix = ""; //base64格式前头
        String data = "";//实体部分数据
        if (base64Data == null || base64Data.isEmpty()) {
            jsonObject.put("success", false);
            jsonObject.put("msg", "上传失败，上传图片数据为空");
            return jsonObject;
        } else {
            String[] d = base64Data.split("base64,");//将字符串分成数组
            if (d.length == 2) {
                dataPrix = d[0];
                data = d[1];
            } else {
                jsonObject.put("success", false);
                jsonObject.put("msg", "上传失败，数据不合法");
                return jsonObject;
            }
        }
        String suffix = "";//图片后缀，用以识别哪种格式数据
        if ("data:image/jpeg;".equalsIgnoreCase(dataPrix)) {
            suffix = ".jpg";
        } else if ("data:image/x-icon;".equalsIgnoreCase(dataPrix)) {
            suffix = ".ico";
        } else if ("data:image/gif;".equalsIgnoreCase(dataPrix)) {
            suffix = ".gif";
        } else if ("data:image/png;".equalsIgnoreCase(dataPrix)) {
            suffix = ".png";
        } else {
            jsonObject.put("success", false);
            jsonObject.put("msg", "上传图片格式不合法");
            return jsonObject;
        }
        String uuid = UUID.randomUUID().toString().replaceAll("-", "");
        String tempFileName = uuid + suffix;
        String imgFilePath = "D:\\picTmp\\" + tempFileName;//新生成的图片
        BASE64Decoder decoder = new BASE64Decoder();
        try {
            //Base64解码
            byte[] b = decoder.decodeBuffer(data);
            for (int i = 0; i < b.length; ++i) {
                if (b[i] < 0) {
                    //调整异常数据
                    b[i] += 256;
                }
            }
            OutputStream out = Files.newOutputStream(Paths.get(imgFilePath));
            out.write(b);
            out.flush();
            out.close();
            jsonObject.put("success", true);
            jsonObject.put("msg", imgFilePath);
            return jsonObject;
        } catch (IOException e) {
            e.printStackTrace();
            jsonObject.put("success", false);
            jsonObject.put("msg", "上传图片失败");
            return jsonObject;
        }
    }
}
