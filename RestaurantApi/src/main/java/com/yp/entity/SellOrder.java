package com.yp.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Data
@NoArgsConstructor
public class SellOrder {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int orderId;
    private int productId;
    private int count;
    private double totalPrice;
    private Timestamp createDate;
    private String tableName;
    private String waiterName;
}
