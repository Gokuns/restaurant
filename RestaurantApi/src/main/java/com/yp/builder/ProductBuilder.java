package com.yp.builder;
import com.yp.entity.Category;
import com.yp.entity.Media;
import com.yp.entity.Product;

import java.util.HashSet;
import java.util.Set;

public class ProductBuilder implements Builder{
    private Long id;
    private String name;
    private String details;
    private Media media;
    private double price;
    private Set<Category> categories;

    public ProductBuilder(){
        this.id=1L;
        this.name="";
        this.details="";
        this.media=new MediaBuilder().build();
        this.price=1;
        this.categories = new HashSet<>();
        this.categories.add(new CategoryBuilder().build());
    }

    @Override
    public ProductBuilder withId(Long id) {
        this.id=id;
        return this;
    }

    @Override
    public ProductBuilder withName(String name) {
        this.name = name;
        return this;
    }


    public ProductBuilder withDetails(String details){
        this.details=details;
        return this;
    }
    public ProductBuilder withMedia(Media media) {
        this.media = media;
        return this;
    }

    public ProductBuilder withPrice(double price){
        this.price=price;
        return this;
    }
    public ProductBuilder withCategory(Set<Category> categories){
        this.categories = categories;
        return this;
    }

    public Product build() {
        Product product=new Product();
        product.setId(this.id);
        product.setName(this.name);
        product.setDetails(this.details);
        product.setMedia(this.media);
        product.setPrice(this.price);
        product.setCategories(this.categories);
        return product;
    }

}
