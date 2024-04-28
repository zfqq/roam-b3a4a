package com.rabbiter.market.service.member_management.member.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.rabbiter.market.domain.goods_management.goods.Goods;
import com.rabbiter.market.domain.member_management.member.Member;
import com.rabbiter.market.domain.member_management.member.MemberCancel;
import com.rabbiter.market.domain.personnel_management.employee.Employee;
import com.rabbiter.market.mapper.member_management.member.SuspclassesMapper;
import com.rabbiter.market.qo.member_management.member.QueryMember;
import com.rabbiter.market.service.goods_management.goods.IGoodsService;
import com.rabbiter.market.service.member_management.member.IMemberService;
import com.rabbiter.market.service.member_management.member.SuspclassesService;
import com.rabbiter.market.service.personnel_management.employee.IEmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Service
public class SuspclassesServiceImpl extends ServiceImpl<SuspclassesMapper, MemberCancel> implements SuspclassesService {
    @Autowired
    private IMemberService memberService;
    @Autowired
    private IGoodsService goodsService;
    @Autowired
    private IEmployeeService employeeService;

    @Override
    public Page<MemberCancel> queryPageByQo(QueryMember qo) {
        Page<MemberCancel> page = new Page<>(qo.getCurrentPage(), qo.getPageSize());

        ArrayList<MemberCancel> memberCancels = new ArrayList<>();
        // 根据会员卡信息查询 会员信息
        String cardnumber = qo.getCardnumber();
        QueryWrapper<Member> memberQueryWrapper = new QueryWrapper<>();
        memberQueryWrapper.eq("cardnumber", cardnumber);
        Member member = memberService.getOne(memberQueryWrapper);
        //根据会员信息查询所有的课程 信息
        if(!StringUtils.isEmpty(member)){
            if(!StringUtils.isEmpty(qo.getType())){
                member.setCourse(qo.getType());
            }
            String[] split = member.getCourse().split(",");
            for (String s : split) {
                MemberCancel memberCancel = new MemberCancel();
                Goods byId = goodsService.getById(Long.valueOf(s));
                memberCancel.setCardnumber(Long.valueOf(member.getCardnumber()));
                memberCancel.setClassname(byId.getName());
                memberCancel.setMembername(member.getName());
                memberCancel.setClassid(byId.getId());
                memberCancel.setSuspend(byId.getSuspend());

                // 课程总课时
                memberCancel.setAllcancee(String.valueOf(byId.getPurchashPrice()));
                QueryWrapper<MemberCancel> cancelQueryWrapper = new QueryWrapper<>();
                cancelQueryWrapper
                        .eq("cardnumber", cardnumber)
                        .eq("classid", s)
                        .orderByDesc("cancetime");
                List<MemberCancel> list = super.list(cancelQueryWrapper);
                if (!list.isEmpty()) {
                    memberCancel.setCancetime(list.get(0).getCancetime());
                    memberCancel.setCancelnum(list.get(0).getCancelnum());
                    memberCancel.setSurpluscancee(String.valueOf(byId.getPurchashPrice() - list.size()));
                    memberCancel.setCanceemname(list.get(0).getCanceemname());
                    memberCancel.setCanceltype(list.get(0).getCanceltype());
                    memberCancel.setCanCancelClass("0");
                    if(Integer.parseInt(memberCancel.getSurpluscancee())<1){
                        memberCancel.setCanCancelClass("1");
                    }
                    if("1".equals(byId.getSuspend())){
                        memberCancel.setCanCancelClass("1");
                    }
                }
                memberCancels.add(memberCancel);
            }
        }
        memberCancels.sort(Comparator.comparing(MemberCancel::getCancetime, Comparator.nullsFirst(Comparator.naturalOrder())).reversed());
        page.setRecords(memberCancels);
        page.setTotal(memberCancels.size());
        return page;
    }

    @Override
    public MemberCancel queryClassById(Long memberid, Long classid) {
        QueryWrapper<Member> memberQueryWrapper = new QueryWrapper<>();
        memberQueryWrapper.eq("cardnumber", memberid);
        Member member = memberService.getOne(memberQueryWrapper);
        Goods byId = goodsService.getById(classid);
        MemberCancel memberCancel = new MemberCancel();
        // 课程名称
        memberCancel.setClassname(byId.getName());
        // 会员卡号
        memberCancel.setCardnumber(memberid);
        // 会员姓名
        memberCancel.setMembername(member.getName());
        memberCancel.setClassid(classid);
        return memberCancel;
    }

    @Override
    public void insertCancelClass(MemberCancel memberCancel) {
        memberCancel.setCancetime(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        Long cancelnum = memberCancel.getCancelnum();
        if (memberCancel.getCancelnum() > 1) {
            memberCancel.setCanceltype(2L);
        } else {
            memberCancel.setCanceltype(1L);
        }
        Employee byId = employeeService.getById(memberCancel.getCanceemid());
        memberCancel.setCanceemname(byId.getNickName());
        for (long i = 0L; i <cancelnum; i++) {
            memberCancel.setCancelnum(1L);
            super.save(memberCancel);
        }
    }

    @Override
    public Page<MemberCancel> queryCourseCancellation(QueryMember qo) {
        Page<MemberCancel> page = new Page<>(qo.getCurrentPage(), qo.getPageSize());
        QueryWrapper<MemberCancel> wrapper = new QueryWrapper<>();
        wrapper.like(StringUtils.hasText(qo.getPhone()), "classid",13);

        super.page(page, wrapper);
        return page;
    }
}
