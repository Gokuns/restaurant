package com.yp.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.sql.Timestamp;


@SQLDelete(
        sql="UPDATE SellOrder SET deleted= true where id=?")
@Where(clause = "deleted=false")
@Entity
@Data
@NoArgsConstructor
public class SellOrder extends BaseEntity{

    private int productId;
    private int count;
    private double totalPrice;
    private Timestamp createDate;
    private String tableName;
    private String waiterName;
    private Long customerId;
}
