package com.yp.builder;

import com.yp.dto.CategoryDto;
import com.yp.dto.MediaDto;
import com.yp.dto.ProductDto;
import com.yp.entity.Category;
import com.yp.entity.Media;
import com.yp.entity.Product;

import java.util.HashSet;
import java.util.Set;

public class ProductDtoBuilder extends Builder{
    private int _id;
    private String _name;
    private String _details;
    private MediaDto _media;
    private double _price;
    private Set<CategoryDto> _categories;

    public ProductDtoBuilder(){
        this._id=1;
        this._name="";
        this._details="";
        this._media=new MediaDtoBuilder().build();
        this._price=1;
        this._categories = new HashSet<>();
        this._categories.add(new CategoryDtoBuilder().build());
    }

    @Override
    public ProductDtoBuilder withId(int id) {
        this._id=id;
        return this;
    }

    @Override
    public ProductDtoBuilder withName(String name) {
        this._name = name;
        return this;
    }


    public ProductDtoBuilder withDetails(String details){
        this._details=details;
        return this;
    }
    public ProductDtoBuilder withMedia(MediaDto media){
        this._media=media;
        return this;
    }
    public ProductDtoBuilder withPrice(double price){
        this._price=price;
        return this;
    }
    public ProductDtoBuilder withCategory(Set<CategoryDto> categories){
        this._categories = categories;
        return this;
    }

    public ProductDto build() {
        ProductDto product=new ProductDto();
        product.setId(this._id);
        product.setName(this._name);
        product.setDetails(this._details);
        product.setMedia(this._media);
        product.setCategories(this._categories);
        return product;
    }

}
