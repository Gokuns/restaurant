package com.yp.builder;

import com.yp.dto.CategoryDto;
import com.yp.dto.MediaDto;
import com.yp.dto.ProductDto;
import java.util.HashSet;
import java.util.Set;

public class ProductDtoBuilder implements Builder{
    private Long id;
    private String name;
    private String details;
    private MediaDto media;
    private double price;
    private Set<CategoryDto> categories;

    public ProductDtoBuilder(){
        this.id=1L;
        this.name="";
        this.details="";
        this.media=new MediaDtoBuilder().build();
        this.price=1;
        this.categories = new HashSet<>();
        this.categories.add(new CategoryDtoBuilder().build());
    }

    @Override
    public ProductDtoBuilder withId(Long id) {
        this.id=id;
        return this;
    }

    @Override
    public ProductDtoBuilder withName(String name) {
        this.name = name;
        return this;
    }


    public ProductDtoBuilder withDetails(String details){
        this.details=details;
        return this;
    }
    public ProductDtoBuilder withMedia(MediaDto media){
        this.media=media;
        return this;
    }
    public ProductDtoBuilder withPrice(double price){
        this.price=price;
        return this;
    }
    public ProductDtoBuilder withCategory(Set<CategoryDto> categories){
        this.categories = categories;
        return this;
    }

    public ProductDto build() {
        ProductDto product=new ProductDto();
        product.setId(this.id);
        product.setName(this.name);
        product.setDetails(this.details);
        product.setPrice(this.price);
        product.setMedia(this.media);
        product.setCategories(this.categories);
        return product;
    }

}
