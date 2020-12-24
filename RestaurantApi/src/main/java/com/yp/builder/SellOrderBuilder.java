package com.yp.builder;

import com.yp.entity.SellOrder;
import liquibase.pro.packaged.T;

import java.sql.Timestamp;

public class SellOrderBuilder extends Builder{
    private Long _id;
    private int _productId;
    private int _count;
    private double _totalPrice;
    private Timestamp _createDate;
    private String _tableName;
    private String _waiterName;

    public SellOrderBuilder(){
        this._id=1L;
        this._productId=1;
        this._count=1;
        this._totalPrice=1;
        this._createDate= new Timestamp(System.currentTimeMillis());
        this._tableName="";
        this._waiterName="";
    }

    @Override
    public SellOrderBuilder withId(Long id) {
        this._id=id;
        return this;
    }
    @Override
    public SellOrderBuilder withName(String name) {
        return this;
    }


    public SellOrderBuilder withProductId(int productId) {
        this._productId=productId;
        return this;
    }

    public SellOrderBuilder withCount(int count) {
        this._count=count;
        return this;
    }
    public SellOrderBuilder withTotalePrice(int totalPrice) {
        this._totalPrice=totalPrice;
        return this;
    }
    public SellOrderBuilder withCreateDate(Timestamp createDate) {
        this._createDate=createDate;
        return this;
    }
    public SellOrderBuilder withTableName(String tableName) {
        this._tableName=tableName;
        return this;
    }
    public SellOrderBuilder withWaiterName(String waiterName) {
        this._waiterName=waiterName;
        return this;
    }

    public SellOrder build() {
        SellOrder sellOrder = new SellOrder();
        sellOrder.setId(this._id);
        sellOrder.setProductId(this._productId);
        sellOrder.setCount(this._count);
        sellOrder.setTotalPrice(this._totalPrice);
        sellOrder.setCreateDate(this._createDate);
        sellOrder.setTableName(this._tableName);
        sellOrder.setWaiterName(this._waiterName);
        return sellOrder;

    }


}
