package com.rabbiter.market.controller.member_management.member;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.rabbiter.market.common.sercurity.annotation.HasPermisson;
import com.rabbiter.market.common.web.response.JsonResult;
import com.rabbiter.market.domain.member_management.member.Member;
import com.rabbiter.market.domain.member_management.member.MemberCancel;
import com.rabbiter.market.qo.member_management.member.QueryMember;
import com.rabbiter.market.service.goods_management.goods.IGoodsService;
import com.rabbiter.market.service.member_management.member.IMemberService;
import com.rabbiter.market.service.member_management.member.SuspclassesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Validated
@RequestMapping("/suspclasses/handover")
public class HandoverController {
    @Autowired
    private IMemberService memberService;
    @Autowired
    private IGoodsService goodsService;
    @Autowired
    private SuspclassesService suspclassesService;

    @HasPermisson("suspclasses:cancel:list")
    @PostMapping("/queryPageByQo")
    public JsonResult queryPageByQo(QueryMember qo) {
        Page<Member> page = memberService.queryPageByQo(qo);
        return JsonResult.success(page);
    }

    /**
     * 查询课程信息 根据会员卡号
     *
     * @param qo
     * @return JsonResult
     */
    @PostMapping("/getConnectList")
    public JsonResult queryClassPageByCo(QueryMember qo) {
        Page<MemberCancel> page = suspclassesService.queryPageByQo(qo);
        return JsonResult.success(page);
    }
    @PostMapping("/queryConnectMembersList")
    public JsonResult queryConnectMembersList(QueryMember qo) {
        Page<MemberCancel> page = suspclassesService.queryPageByQo(qo);
        return JsonResult.success(page);
    }
    @PostMapping("/getHandoveCourse")
    public JsonResult getHandoveCourse(QueryMember qo) {
        Page<MemberCancel> page = suspclassesService.queryPageByQo(qo);
        return JsonResult.success(page);
    }
    /**
     * 消课详情查询
     * @return JsonResult
     */

    @PostMapping("/queryClassById")
    public JsonResult queryClassById(Long memberid,Long classid){
        MemberCancel memberCancel = suspclassesService.queryClassById(memberid,classid);
        return JsonResult.success(memberCancel);
    }/**
     * 消课详情查询
     * @return JsonResult
     */

    @PostMapping("/inertcancelclass")
    public JsonResult inertcancelclass(MemberCancel memberCancel){
        suspclassesService.insertCancelClass(memberCancel);
        return JsonResult.success(memberCancel);
    }
    @PostMapping("/queryCourseCancellation")
    public JsonResult queryCourseCancellation(QueryMember qo) {
        Page<MemberCancel> page = suspclassesService.queryCourseCancellation(qo);
        return JsonResult.success(page);
    }
}
