package com.rabbiter.market.domain.member_management.member;


import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;

@Data
@TableName(value = "t_connect_course")
public class TConnectCourse {
    @TableField(value = "id")
    @ExcelProperty(index = 1, value = "")
    private Long id;
    /**
     * 姓名
     */
    @TableField(value = "name")
    @ExcelProperty(index = 2, value = "姓名")
    private String name;
    /**
     * 手机号码
     */
    @TableField(value = "phone")
    @ExcelProperty(index = 3, value = "手机号码")
    private String phone;
    /**
     * 身份证
     */
    @TableField(value = "cardid")
    @ExcelProperty(index = 4, value = "身份证")
    private String cardid;
    /**
     * 卡号
     */
    @TableField(value = "number")
    @ExcelProperty(index = 5, value = "卡号")
    private String number;
    /**
     * 合同教练
     */
    @TableField(value = "contractcoaching")
    @ExcelProperty(index = 6, value = "合同教练")
    private String contractcoaching;
    /**
     * 课程类型
     */
    @TableField(value = "coursetype")
    @ExcelProperty(index = 7, value = "课程类型")
    private String coursetype;
    /**
     * 课程类型
     */
    @TableField(value = "coursename")
    @ExcelProperty(index = 8, value = "课程类型")
    private String coursename;
    /**
     * 剩余次数
     */
    @TableField(value = "remainingtime")
    @ExcelProperty(index = 9, value = "剩余次数")
    private String remainingtime;
    /**
     * 购买次数
     */
    @TableField(value = "coursetimes")
    @ExcelProperty(index = 10, value = "购买次数")
    private String coursetimes;
    /**
     * 购买时间
     */
    @TableField(value = "contracttime")
    @ExcelProperty(index = 11, value = "购买时间")
    private String contracttime;
    /**
     * 到期时间
     */
    @TableField(value = "contractendtime")
    @ExcelProperty(index = 12, value = "到期时间")
    private String contractendtime;
    /**
     * 单价金额
     */
    @TableField(value = "amount")
    @ExcelProperty(index = 13, value = "单价金额")
    private String amount;
    /**
     * 备注
     */
    @TableField(value = "remarks")
    @ExcelProperty(index = 14, value = "备注")
    private String remarks;
}