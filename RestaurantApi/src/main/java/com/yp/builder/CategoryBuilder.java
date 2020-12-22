package com.yp.builder;

import com.yp.entity.Category;
import com.yp.entity.Media;
import com.yp.entity.Product;

import java.util.*;

public class CategoryBuilder extends Builder{
    private int _id;
    private String _name;
    private Media _media;

    public CategoryBuilder(){
        this._id=1;
        this._name="";
        this._media = new MediaBuilder().build();
    }


    @Override
    public CategoryBuilder withId(int id) {
        this._id=id;
        return this;
    }

    @Override
    public CategoryBuilder withName(String name) {
        this._name=name;
        return this;
    }

    public CategoryBuilder withMedia(Media media){
        this._media = media;
        return this;
    }




    public Category build(){
        Category category = new Category();
        category.setId(this._id);
        category.setName(this._name);
        category.setMedia(this._media);
        return category;
    }
}
