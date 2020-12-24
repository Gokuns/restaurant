package com.yp.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@SQLDelete(
        sql="UPDATE Customer SET deleted= true where id=?")
@Where(clause = "deleted=false")
@Entity
@Data
@NoArgsConstructor
public class Customer extends BaseEntity{

    private String name;
    private String surname;
    private String phone;
    private String address;
}
