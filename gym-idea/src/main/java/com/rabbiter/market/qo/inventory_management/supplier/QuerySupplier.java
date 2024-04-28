package com.rabbiter.market.qo.inventory_management.supplier;

import com.rabbiter.market.qo.BaseQuery;

public class QuerySupplier extends BaseQuery {
    private String name;
    private String address;
    private String info;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }
}
