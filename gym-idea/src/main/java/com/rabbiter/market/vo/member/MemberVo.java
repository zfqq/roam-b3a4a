package com.rabbiter.market.vo.member;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.write.style.ColumnWidth;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.io.Serializable;

/**
 * @program: gym-idea
 * @ClassName: MemberVo
 * @description:
 * @author: zhaofeng
 * @create: 2024-04-30 16:30
 */
@Data
@ColumnWidth(20)
public class MemberVo implements Serializable {

    @ExcelProperty(index = 0,value = "会员姓名")
    private String name;
    @ExcelProperty(index = 1,value = "会员手机号码")
    private String phone;
    private String password;
    private String email;
    private Long integral;
    private String state;
    private String info;
    private String cardnumber;
    @ExcelProperty(index = 2,value = "会员年龄")
    private String age;
    @ExcelProperty(index = 3,value = "会员性别")
    private String sex;
    private String level;
    private String course;
    private String amount;
    private String type;
    private Long instructor;
    private String image;


}