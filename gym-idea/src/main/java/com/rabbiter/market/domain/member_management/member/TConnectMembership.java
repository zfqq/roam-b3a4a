package com.rabbiter.market.domain.member_management.member;


import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;

@Data
@TableName(value = "t_connect_membership")
public class TConnectMembership {
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
     * 定金收取/退款金额
     */
    @TableField(value = "amount")
    @ExcelProperty(index = 4, value = "定金收取/退款金额")
    private String amount;
    /**
     * 使用类型
     */
    @TableField(value = "membertype")
    @ExcelProperty(index = 5, value = "使用类型")
    private String membertype;
    /**
     * 销售人
     */
    @TableField(value = "sellers")
    @ExcelProperty(index = 6, value = "销售人 ")
    private String sellers;
    /**
     * 收款日/退款日
     */
    @TableField(value = "receivingdate")
    @ExcelProperty(index = 7, value = "收款日/退款日")
    private String receivingdate;
    /**
     * 到期日
     */
    @TableField(value = "contractendtime")
    @ExcelProperty(index = 8, value = "到期日")
    private String contractendtime;
    /**
     * 支付小计
     */
    @TableField(value = "remainingtime")
    @ExcelProperty(index = 9, value = "支付小计")
    private String remainingtime;
    /**
     * 收款人/退款人
     */
    @TableField(value = "payee")
    @ExcelProperty(index = 10, value = "收款人/退款人")
    private String payee;
    /**
     * 当前状态
     */
    @TableField(value = "status")
    @ExcelProperty(index = 11, value = "当前状态")
    private String status;
    /**
     * 操作人员
     */
    @TableField(value = "operators")
    @ExcelProperty(index = 12, value = "操作人员")
    private String operators;
    /**
     * 操作时间
     */
    @TableField(value = "operattime")
    @ExcelProperty(index = 13, value = "操作时间")
    private String operattime;
    /**
     * 备注
     */
    @TableField(value = "remarks")
    @ExcelProperty(index = 14, value = "备注")
    private Byte[] remarks;
    /**
     * 来源
     */
    @TableField(value = "source")
    @ExcelProperty(index = 15, value = "来源")
    private String source;
    /**
     * 门店
     */
    @TableField(value = "stores")
    @ExcelProperty(index = 16, value = "门店")
    private String stores;
    /**
     * 延期天数
     */
    @TableField(value = "extensiondays")
    @ExcelProperty(index = 17, value = "延期天数")
    private String extensiondays;
}