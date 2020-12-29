package com.yp.builder;

import com.yp.dto.SellOrderDto;
import java.sql.Timestamp;

public class SellOrderDtoBuilder implements Builder{
    private Long id;
    private int productId;
    private int count;
    private double totalPrice;
    private Timestamp createDate;
    private String tableName;
    private String waiterName;

    public SellOrderDtoBuilder(){
        this.id=1L;
        this.productId=1;
        this.count=1;
        this.totalPrice=1;
        this.createDate= new Timestamp(System.currentTimeMillis());
        this.tableName="";
        this.waiterName="";
    }

    @Override
    public SellOrderDtoBuilder withId(Long id) {
        this.id=id;
        return this;
    }
    @Override
    public SellOrderDtoBuilder withName(String name) {
        return this;
    }


    public SellOrderDtoBuilder withProductId(int productId) {
        this.productId=productId;
        return this;
    }

    public SellOrderDtoBuilder withCount(int count) {
        this.count=count;
        return this;
    }
    public SellOrderDtoBuilder withTotalePrice(int totalPrice) {
        this.totalPrice=totalPrice;
        return this;
    }
    public SellOrderDtoBuilder withCreateDate(Timestamp createDate) {
        this.createDate=createDate;
        return this;
    }
    public SellOrderDtoBuilder withTableName(String tableName) {
        this.tableName=tableName;
        return this;
    }
    public SellOrderDtoBuilder withWaiterName(String waiterName) {
        this.waiterName=waiterName;
        return this;
    }

    public SellOrderDto build() {
        SellOrderDto sellOrder = new SellOrderDto();
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
