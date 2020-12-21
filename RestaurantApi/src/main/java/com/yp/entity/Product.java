package com.yp.entity;



import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
public class Product {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int id;
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
