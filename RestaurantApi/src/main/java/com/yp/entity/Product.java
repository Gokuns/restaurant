package com.yp.entity;



import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.util.Set;

@EqualsAndHashCode(callSuper = true)
@Entity(name = "PRODUCTS")
@Data
@NoArgsConstructor
@SQLDelete(
        sql="UPDATE PRODUCTS SET DELETED= true WHERE ID=?")
@Where(clause = "DELETED=false")
public class Product extends BaseEntity{

    @Column(name = "NAME")
    private String name;
    @Column(name = "DETAILS")
    private String details;
    @Column(name = "PRICE")
    private double price;

    @ManyToMany
    @JoinTable(name = "PRODUCT_CATEGORIES",
    joinColumns = {@JoinColumn(name="PRODUCT_ID")},
    inverseJoinColumns = {@JoinColumn(name = "CATEGORY_ID")})
    private Set<Category> categories;

    @ManyToOne
    @JoinColumn(name= "MEDIA_ID")
    private Media media;

  }
