package com.rabbiter.market.domain.goods_management.goods_category;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;

/**
 * 商品分类
 */
@TableName("goods_category")
public class GoodsCategory implements Serializable {
    public static  final String  STATE_NORMAL="0";
    public static  final String  STATE_BAN="-1";
    @TableId(type = IdType.AUTO)
    private Long id;
    private String name;
    private String info;
    private String state;
    private String suspend;

    public GoodsCategory() {
    }

    public GoodsCategory(Long id, String name, String info, String state,String suspend) {
        this.id = id;
        this.name = name;
        this.info = info;
        this.state = state;
        this.suspend = suspend;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getSuspend() {
        return suspend;
    }

    public void setSuspend(String suspend) {
        this.suspend = suspend;
    }
}