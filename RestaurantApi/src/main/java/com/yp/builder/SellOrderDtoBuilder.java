package com.yp.builder;

import com.yp.dto.SellOrderDto;
import com.yp.entity.SellOrder;

import java.sql.Timestamp;

public class SellOrderDtoBuilder extends Builder{
    private int _orderId;
    private int _productId;
    private int _count;
    private double _totalPrice;
    private Timestamp _createDate;
    private String _tableName;
    private String _waiterName;

    public SellOrderDtoBuilder(){
        this._orderId=1;
        this._productId=1;
        this._count=1;
        this._totalPrice=1;
        this._createDate= new Timestamp(System.currentTimeMillis());
        this._tableName="";
        this._waiterName="";
    }

    @Override
    public SellOrderDtoBuilder withId(int id) {
        return null;
    }
    @Override
    public SellOrderDtoBuilder withName(String name) {
        return this;
    }


    public SellOrderDtoBuilder withOrderId(int orderId) {
        this._orderId=orderId;
        return this;
    }


    public SellOrderDtoBuilder withProductId(int productId) {
        this._productId=productId;
        return this;
    }

    public SellOrderDtoBuilder withCount(int count) {
        this._count=count;
        return this;
    }
    public SellOrderDtoBuilder withTotalePrice(int totalPrice) {
        this._totalPrice=totalPrice;
        return this;
    }
    public SellOrderDtoBuilder withCreateDate(Timestamp createDate) {
        this._createDate=createDate;
        return this;
    }
    public SellOrderDtoBuilder withTableName(String tableName) {
        this._tableName=tableName;
        return this;
    }
    public SellOrderDtoBuilder withWaiterName(String waiterName) {
        this._waiterName=waiterName;
        return this;
    }

    public SellOrderDto build() {
        SellOrderDto sellOrder = new SellOrderDto();
        sellOrder.setOrderId(this._orderId);
        sellOrder.setProductId(this._productId);
        sellOrder.setCount(this._count);
        sellOrder.setTotalPrice(this._totalPrice);
        sellOrder.setCreateDate(this._createDate);
        sellOrder.setTableName(this._tableName);
        sellOrder.setWaiterName(this._waiterName);
        return sellOrder;

    }
}
