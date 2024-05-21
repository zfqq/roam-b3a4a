package com.rabbiter.market.service.member_management.member.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.rabbiter.market.domain.member_management.member.TConnectMember;
import com.rabbiter.market.mapper.member_management.member.TConnectMemberMapper;
import com.rabbiter.market.qo.member_management.member.QueryMember;
import com.rabbiter.market.service.member_management.member.HandoverService;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;

@Service
public class HandoverServiceImpl  extends ServiceImpl<TConnectMemberMapper, TConnectMember> implements HandoverService {
    @Override
    public Page<TConnectMember> queryPageByQo(QueryMember qo) {
        Page<TConnectMember> page = new Page<>(qo.getCurrentPage(), qo.getPageSize());
        QueryWrapper<TConnectMember> wrapper = new QueryWrapper<>();
        wrapper.like(StringUtils.hasText(qo.getPhone()), "phone", qo.getPhone());
        wrapper.like(StringUtils.hasText(qo.getName()), "name", qo.getName());
        wrapper.eq(StringUtils.hasText(qo.getCardnumber()), "number", qo.getCardnumber());
        super.page(page, wrapper);
        // todo 处理数据
        // 处理课程
        // 处理 过期时间
        List<TConnectMember> records = page.getRecords();
        records.forEach(c->{
            try {
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                Date date1 = null;
                Date date2 = null;
                try {
                    date1 = sdf.parse(c.getContractendtime());
                    date2 = sdf.parse(LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
                } catch (ParseException e) {
                    throw new RuntimeException(e);
                }
                long long1 =date1.getTime();
                long long2= date2.getTime();
                if (long1<long2) {
                     c.setOverduetime("1");
                }else{
                    c.setOverduetime("0");
                }
            } catch (RuntimeException e) {
                c.setOverduetime("0");
                throw new RuntimeException(e);
            }

        });
        return page;
    }

    @Override
    public String inertcancelclass(TConnectMember qo) {
        try {
            TConnectMember byId = super.getById(qo.getId());
            if(!StringUtils.isEmpty(byId) && !StringUtils.isEmpty(byId.getRemainingtime())){
                if(Integer.parseInt(byId.getRemainingtime()) >0){
                    QueryWrapper<TConnectMember> wrapper = new QueryWrapper<>();
                    wrapper.eq("id",qo.getId());
                    TConnectMember tConnectMember = new TConnectMember();
                    tConnectMember.setRemainingtime(String.valueOf(Integer.parseInt(byId.getRemainingtime())-1));
                    boolean update = super.update(tConnectMember, wrapper);
                    if(update){
                        return "1";
                    }else{
                        return "0";
                    }
                }else{
                    return "0";
                }
            }else{
                return "0";
            }
        } catch (NumberFormatException e) {
            return "0";
        }
    }
}
