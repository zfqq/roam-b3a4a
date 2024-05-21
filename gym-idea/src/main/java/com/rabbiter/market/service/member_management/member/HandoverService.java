package com.rabbiter.market.service.member_management.member;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.rabbiter.market.domain.member_management.member.TConnectMember;
import com.rabbiter.market.qo.member_management.member.QueryMember;

public interface HandoverService {
    Page<TConnectMember> queryPageByQo(QueryMember qo);

    String inertcancelclass(TConnectMember qo);
}
