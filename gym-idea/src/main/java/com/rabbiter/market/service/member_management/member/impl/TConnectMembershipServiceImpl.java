package com.rabbiter.market.service.member_management.member.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.rabbiter.market.domain.member_management.member.TConnectMembership;
import com.rabbiter.market.mapper.member_management.member.TConnectMembershipMapper;
import com.rabbiter.market.service.member_management.member.TConnectMembershipService;
import org.springframework.stereotype.Service;

@Service
public class TConnectMembershipServiceImpl extends ServiceImpl<TConnectMembershipMapper, TConnectMembership> implements TConnectMembershipService {
}
