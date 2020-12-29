package com.yp.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;

@EqualsAndHashCode(callSuper = true)
@SQLDelete(
        sql="UPDATE CUSTOMERS SET DELETED= true WHERE ID=?")
@Where(clause = "DELETED=false")
@Entity(name = "CUSTOMERS")
@Data
@NoArgsConstructor
public class Customer extends BaseEntity{

    @Column(name = "NAME")
    private String name;
    @Column(name = "SURNAME")
    private String surname;
    @Column(name = "PHONE")
    private String phone;
    @Column(name = "ADDRESS")
    private String address;
    @ManyToOne
    @JoinColumn(name= "MEDIA_ID")
    private Media media;
}
