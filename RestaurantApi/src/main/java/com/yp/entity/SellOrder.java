package com.yp.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
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
    @NotNull(message = "SellOrder productId cannot be null")
    @NotBlank(message = "SellOrder productId cannot be blank")
    private int productId;
    @Column(name = "COUNT")
    @NotNull(message = "SellOrder count cannot be null")
    @NotBlank(message = "SellOrder count cannot be blank")
    private int count;
    @Column(name = "TOTAL_PRICE")
    @NotNull(message = "SellOrder totalPrice cannot be null")
    @NotBlank(message = "SellOrder totalPrice cannot be blank")
    private double totalPrice;
    @Column(name = "CREATE_DATE")
    @NotNull(message = "SellOrder createDate cannot be null")
    @NotBlank(message = "SellOrder createDate cannot be blank")
    private Timestamp createDate;
    @Column(name = "TABLE_NAME")
    private String tableName;
    @Column(name = "WAITER_NAME")
    private String waiterName;
    @Column(name = "CUSTOMER_ID")
    private Long customerId;
}
