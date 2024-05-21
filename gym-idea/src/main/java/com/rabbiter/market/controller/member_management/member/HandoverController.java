package com.rabbiter.market.controller.member_management.member;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.rabbiter.market.common.sercurity.annotation.HasPermisson;
import com.rabbiter.market.common.web.response.JsonResult;
import com.rabbiter.market.domain.member_management.member.Member;
import com.rabbiter.market.domain.member_management.member.MemberCancel;
import com.rabbiter.market.domain.member_management.member.TConnectMember;
import com.rabbiter.market.qo.member_management.member.QueryMember;
import com.rabbiter.market.service.goods_management.goods.IGoodsService;
import com.rabbiter.market.service.member_management.member.HandoverService;
import com.rabbiter.market.service.member_management.member.IMemberService;
import com.rabbiter.market.service.member_management.member.SuspclassesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@Validated
@RequestMapping("/suspclasses/handover")
public class HandoverController {
    @Autowired
    private HandoverService memberService;

    /**
     * 查询课程信息 根据会员卡号
     *
     * @param qo
     * @return JsonResult
     */
    @PostMapping("/getConnectList")
    public  JsonResult queryClassPageByCo(QueryMember qo) {
        Page<TConnectMember> page = memberService.queryPageByQo(qo);
        return JsonResult.success(page);
    }  /**
     * 查询课程信息 根据会员卡号
     *
     * @param qo
     * @return JsonResult
     */
    @PostMapping("/inertcancelclass")
    public  JsonResult inertcancelclass(TConnectMember qo) {
        String inertcancelclass = memberService.inertcancelclass(qo);
        return JsonResult.success(inertcancelclass);
    }
}
