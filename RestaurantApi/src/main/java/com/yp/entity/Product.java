package com.yp.entity;



import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.util.Set;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@NoArgsConstructor
@SQLDelete(
        sql="UPDATE Product SET deleted= true where id=?")
@Where(clause = "deleted=false")
public class Product extends BaseEntity{

    private String name;
    private String details;

    private double price;

    @ManyToMany
    @JoinTable(name = "PRODUCT_CATEGORIES",
    joinColumns = {@JoinColumn(name="product_id")},
    inverseJoinColumns = {@JoinColumn(name = "category_id")})
    private Set<Category> categories;

    @ManyToOne
    @JoinColumn(name= "media_id")
    private Media media;

  }
