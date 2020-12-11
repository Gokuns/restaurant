package com.yp.builder;

import com.yp.dto.CategoryDto;
import com.yp.dto.MediaDto;
import com.yp.entity.Category;

public class CategoryDtoBuilder extends  Builder{
    private int _id;
    private String _name;
    private MediaDto _media;

    public CategoryDtoBuilder(){
        this._id=1;
        this._name="";
        this._media=new MediaDtoBuilder().build();
    }


    @Override
    public CategoryDtoBuilder withId(int id) {
        this._id=id;
        return this;
    }

    @Override
    public CategoryDtoBuilder withName(String name) {
        this._name=name;
        return this;
    }

    public CategoryDtoBuilder withMedia(MediaDto media){
        this._media=media;
        return this;
    }

    public CategoryDto build(){
        CategoryDto category = new CategoryDto();
        category.setId(this._id);
        category.setName(this._name);
        category.setMedia(this._media);
        return category;
    }
}
