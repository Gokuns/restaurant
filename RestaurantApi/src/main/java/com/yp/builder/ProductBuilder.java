package com.yp.builder;

import com.yp.entity.Category;
import com.yp.entity.Product;

import java.util.HashSet;
import java.util.Set;

public class ProductBuilder extends Builder{
    private int _id;
    private String _name;
    private String _details;
    private String _img;
    private double _price;
    private Set<Category> _categories;

    public ProductBuilder(){
        this._id=1;
        this._name="";
        this._details="";
        this._img="";
        this._price=1;
        this._categories = new HashSet<>();
        this._categories.add(new CategoryBuilder().build());
    }

    @Override
    public ProductBuilder withId(int id) {
        this._id=id;
        return this;
    }

    @Override
    public ProductBuilder withName(String name) {
        this._name = name;
        return this;
    }


    public ProductBuilder withDetails(String details){
        this._details=details;
        return this;
    }
    public ProductBuilder withImg(String img){
        this._img=img;
        return this;
    }
    public ProductBuilder withPrice(double price){
        this._price=price;
        return this;
    }
    public ProductBuilder withCategory(Set<Category> categories){
        this._categories = categories;
        return this;
    }

    public Product build() {
        Product product=new Product();
        product.setId(this._id);
        product.setName(this._name);
        product.setDetails(this._details);
        product.setImg(this._img);
        product.setCategories(this._categories);
        return product;
    }

}
