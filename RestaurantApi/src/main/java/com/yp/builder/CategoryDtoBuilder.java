package com.yp.builder;

import com.yp.dto.CategoryDto;
import com.yp.dto.MediaDto;

public class CategoryDtoBuilder implements  Builder{
    private Long id;
    private String name;
    private MediaDto media;

    public CategoryDtoBuilder(){
        this.id=1L;
        this.name="";
        this.media=new MediaDtoBuilder().build();
    }


    @Override
    public CategoryDtoBuilder withId(Long id) {
        this.id=id;
        return this;
    }

    @Override
    public CategoryDtoBuilder withName(String name) {
        this.name=name;
        return this;
    }

    public CategoryDtoBuilder withMedia(MediaDto media){
        this.media=media;
        return this;
    }

    public CategoryDto build(){
        CategoryDto category = new CategoryDto();
        category.setId(this.id);
        category.setName(this.name);
        category.setMedia(this.media);
        return category;
    }
}
