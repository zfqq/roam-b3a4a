package com.rabbiter.market.domain.member_management.member;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.rabbiter.market.domain.goods_management.goods.Goods;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
@TableName("t_member")
public class Member implements Serializable {
    public static final String STATE_NORMAL = "0";
    public static final String STATE_BAN = "1";
    public static  final String DEFAULT_PWD="123456";

    @TableId(type = IdType.AUTO)
    private Long id;
    private String name;
    private String phone;
    @JsonIgnore
    private String password;
    private String email;
    private Long integral;
    private String state;
    private String info;

    private String cardnumber;
    private String age;
    private String sex;
    private String level;
    private String course;
    private String amount;
    private String type;
    private Long instructor;
    private String image;


}
