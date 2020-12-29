package com.yp.builder;

import com.yp.entity.SellOrder;

import java.sql.Timestamp;

public class SellOrderBuilder implements Builder{
    private Long id;
    private int productId;
    private int count;
    private double totalPrice;
    private Timestamp createDate;
    private String tableName;
    private String waiterName;

    public SellOrderBuilder(){
        this.id=1L;
        this.productId=1;
        this.count=1;
        this.totalPrice=1;
        this.createDate= new Timestamp(System.currentTimeMillis());
        this.tableName="";
        this.waiterName="";
    }

    @Override
    public SellOrderBuilder withId(Long id) {
        this.id=id;
        return this;
    }
    @Override
    public SellOrderBuilder withName(String name) {
        return this;
    }


    public SellOrderBuilder withProductId(int productId) {
        this.productId=productId;
        return this;
    }

    public SellOrderBuilder withCount(int count) {
        this.count=count;
        return this;
    }
    public SellOrderBuilder withTotalePrice(int totalPrice) {
        this.totalPrice=totalPrice;
        return this;
    }
    public SellOrderBuilder withCreateDate(Timestamp createDate) {
        this.createDate=createDate;
        return this;
    }
    public SellOrderBuilder withTableName(String tableName) {
        this.tableName=tableName;
        return this;
    }
    public SellOrderBuilder withWaiterName(String waiterName) {
        this.waiterName=waiterName;
        return this;
    }

    public SellOrder build() {
        SellOrder sellOrder = new SellOrder();
        sellOrder.setId(this.id);
        sellOrder.setProductId(this.productId);
        sellOrder.setCount(this.count);
        sellOrder.setTotalPrice(this.totalPrice);
        sellOrder.setCreateDate(this.createDate);
        sellOrder.setTableName(this.tableName);
        sellOrder.setWaiterName(this.waiterName);
        return sellOrder;

    }


}
