package com.rabbiter.market.domain.member_management.member;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("t_connect_course")
public class MemberConnectCourse {
    private Long id;
    private String name;
    private String phone;
    private String cardid;
    private String number;
    private String contractcoaching;
    private String coursetype;
    private String coursename;
    private String remainingtime;
    private String coursetimes;
    private String contracttime;
    private String contractendtime;
    private String amount;
    private String remarks;
}
