package com.rabbiter.market.domain.member_management.member;


import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;

@Data
@TableName(value = "t_connect_member")
public class TConnectMember {
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
     * 卡类型
     */
    @TableField(value = "membertype")
    @ExcelProperty(index = 6, value = "卡类型")
    private String membertype;
    /**
     * 剩余次数
     */
    @TableField(value = "remainingtime")
    @ExcelProperty(index = 7, value = "剩余次数")
    private String remainingtime;
    /**
     * 签约时间
     */
    @TableField(value = "signingtime")
    @ExcelProperty(index = 8, value = "签约时间")
    private String signingtime;
    /**
     * 合同开始时间
     */
    @TableField(value = "contracttime")
    @ExcelProperty(index = 9, value = "合同开始时间")
    private String contracttime;
    /**
     * 合同结束时间
     */
    @TableField(value = "contractendtime")
    @ExcelProperty(index = 10, value = "合同结束时间")
    private String contractendtime;
    /**
     * 金额
     */
    @TableField(value = "amount")
    @ExcelProperty(index = 11, value = "金额")
    private String amount;
    /**
     * 备注
     */
    @ExcelProperty(index = 12, value = "备注")
    @TableField(exist = false)
    private Byte[] remarks;
    /**
     * 来源
     */
    @TableField(value = "source")
    @ExcelProperty(index = 13, value = "来源")
    private String source;
    @TableField(value = "contractcoach")
    private String contractcoach;
    @TableField(value = "coursetype")
    private String coursetype;
    @TableField(value = "coursename")
    private String coursename;
    @TableField(value = "remainingtimes")
    private String remainingtimes;
    @TableField(value = "purchasetime")
    private String purchasetime;
    @TableField(value = "contractcoaching")
    private String contractcoaching;
    @TableField(value = "unitpriceamount")
    private String unitpriceamount;
    @TableField(value = "usagetype")
    private String usagetype;
    @TableField(value = "salesperson")
    private String salesperson;
    @TableField(value = "refunddate")
    private String refunddate;
    @TableField(value = "paymentsubtotal")
    private String paymentsubtotal;
    @TableField(value = "refundrecipient")
    private String refundrecipient;
    @TableField(value = "status")
    private String status;
    @TableField(value = "operators")
    private String operators;
    @TableField(value = "operationtime")
    private String operationtime;
    @TableField(value = "stores")
    private String stores;
    @TableField(value = "extensiondays")
    private String extensiondays;
    @TableField(value = "purchasefrequency")
    private String purchasefrequency;
    @TableField(exist = false)
    private String overduetime;
}