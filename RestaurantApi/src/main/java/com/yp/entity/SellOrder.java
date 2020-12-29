package com.yp.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.sql.Timestamp;

@EqualsAndHashCode(callSuper = true)
@SQLDelete(
        sql="UPDATE SELL_ORDERS SET DELETED= true WHERE ID=?")
@Where(clause = "DELETED=false")
@Entity(name = "SELL_ORDERS")
@Data
@NoArgsConstructor
public class SellOrder extends BaseEntity{

    @Column(name = "PRODUCT_ID")
    private int productId;
    @Column(name = "COUNT")
    private int count;
    @Column(name = "TOTAL_PRICE")
    private double totalPrice;
    @Column(name = "CREATE_DATE")
    private Timestamp createDate;
    @Column(name = "TABLE_NAME")
    private String tableName;
    @Column(name = "WAITER_NAME")
    private String waiterName;
    @Column(name = "CUSTOMER_ID")
    private Long customerId;
}
