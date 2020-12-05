package com.yp.entity;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
public class SellOrder {
    @Id
    @GeneratedValue
    private int orderId;
    private int productId;
    private int count;
    private double totalPrice;
    private Timestamp createDate = new Timestamp(System.currentTimeMillis());



    public SellOrder() {
    }
    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public Timestamp getCreationDateTime() {
        return createDate;
    }

//    public void setCreationDateTime(Timestamp creationDateTime) {
//        this.createDate = creationDateTime;
//    }

}
