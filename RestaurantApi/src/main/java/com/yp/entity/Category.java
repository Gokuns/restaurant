package com.yp.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Set;

@Entity(name = "CATEGORIES")
@Data
@NoArgsConstructor
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    @JsonIgnore
    @ManyToMany(
            mappedBy = "categories"
        )
    private Set<Product> products;

    @ManyToOne
    @JoinColumn(name= "media_id")
    private Media media;

  }
