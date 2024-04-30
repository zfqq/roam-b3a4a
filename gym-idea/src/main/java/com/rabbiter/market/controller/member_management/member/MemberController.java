package com.rabbiter.market.controller.member_management.member;

import com.rabbiter.market.common.sercurity.annotation.HasPermisson;
import com.rabbiter.market.common.web.response.JsonResult;
import com.rabbiter.market.domain.member_management.member.Member;
import com.rabbiter.market.qo.member_management.member.QueryMember;
import com.rabbiter.market.service.goods_management.goods.IGoodsService;
import com.rabbiter.market.service.goods_management.goods.impl.GoodsServiceImpl;
import com.rabbiter.market.service.member_management.member.IMemberService;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

@RestController
@Validated
@RequestMapping("/member_management/member")
public class MemberController {
    @Autowired
    private IMemberService memberService;
    @Autowired
    private IGoodsService goodsService;

    /*查询信息*/
    @HasPermisson("member_management:member:list")
    @PostMapping("/queryPageByQo")
    public JsonResult queryPageByQo(QueryMember qo) {
        Page<Member> page = memberService.queryPageByQo(qo);
        return JsonResult.success(page);
    }

    @HasPermisson("member_management:member:delMember")
    @PostMapping("/delMember")
    public JsonResult delMember(Long id) {
        memberService.delMember(id);
        return JsonResult.success();
    }

    @HasPermisson("member_management:member:save")
    @PostMapping("/save")
    public JsonResult save(Member member) {
        memberService.saveMember(member);
        return JsonResult.success();
    }


    @GetMapping("/queryMemberById")
    public JsonResult queryMemberById(Long id) {
        Member member = memberService.queryMemberById(id);
        return JsonResult.success(member);
    }

    @HasPermisson("member_management:member:update")
    @PostMapping("/update")
    public JsonResult updateMember(Member member) {
        memberService.updateMember(member);
        return JsonResult.success();
    }

    @GetMapping("/queryMemberByPhone")
    public JsonResult queryMemberByPhone(String phone) {
        Member member = memberService.queryMemberByPhone(phone);
        return JsonResult.success(member);
    }
    @GetMapping("/selected_goodsAll")
    public JsonResult selected_goodsAll(){
        List<Map<String,Object>> list=goodsService.selected_goodsAll();
        return JsonResult.success(list);
    }
    @PostMapping("/reportExportExcel")
    public void reportExportExcel(QueryMember qo, HttpServletRequest request, HttpServletResponse response) {
        memberService.reportExportExcel(qo, request, response);

    }
}
