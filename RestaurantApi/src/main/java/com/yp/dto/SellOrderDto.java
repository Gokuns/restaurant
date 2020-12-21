package com.yp.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@NoArgsConstructor
public class SellOrderDto {
    private int orderId;
    private int productId;
    private int count;
    private double totalPrice;
    private Timestamp createDate;
    private String tableName;
    private String waiterName;
}
