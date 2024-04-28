package com.rabbiter.market.service.member_management.member;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.rabbiter.market.domain.member_management.member.MemberCancel;
import com.rabbiter.market.qo.member_management.member.QueryMember;

public interface SuspclassesService  extends IService<MemberCancel> {
    Page<MemberCancel> queryPageByQo(QueryMember qo);

    MemberCancel queryClassById(Long memberid, Long classid);

    void insertCancelClass(MemberCancel memberCancel);

    Page<MemberCancel> queryCourseCancellation(QueryMember qo);
}
