package com.rabbiter.market.qo.member_management.member;

import com.rabbiter.market.qo.BaseQuery;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class QueryMember extends BaseQuery {
    private String phone;
    private String state;
    private String name;

    private String cardnumber;
    private String type;
}
