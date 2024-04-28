package com.rabbiter.market.mapper.member_management.member;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.rabbiter.market.domain.member_management.member.Member;
import com.rabbiter.market.domain.member_management.member.MemberCancel;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface SuspclassesMapper extends BaseMapper<MemberCancel> {
}
