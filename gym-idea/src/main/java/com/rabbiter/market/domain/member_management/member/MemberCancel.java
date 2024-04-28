package com.rabbiter.market.domain.member_management.member;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("t_memberCancel")
public class MemberCancel {
    // 会员卡
    private Long cardnumber;
    // 课程名称
    private String classname;
    // 课程id
    private Long classid;
    // 上次 消课时间
    private String cancetime;
    // 上次 消课教练  id
    private String canceemid;
    // 上次 消课教练  name
    private String canceemname;
    @TableField(exist = false)
    // 总 课程
    private String allcancee;
    @TableField(exist = false)
    // 剩余课程
    private String surpluscancee;
    // 会员姓名
    private String membername;
    // 消课节数
    private Long cancelnum;
    // 1表示单个消课,2表示一次消课多次
    private Long canceltype;
    // 是否可以继续消课
    @TableField(exist = false)
    private String canCancelClass;
    // 是否可以继续消课
    @TableField(exist = false)
    private String suspend;
}
