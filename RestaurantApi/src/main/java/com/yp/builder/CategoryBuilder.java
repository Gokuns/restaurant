package com.yp.builder;

import com.yp.entity.Category;
import com.yp.entity.Media;

public class CategoryBuilder implements Builder{
    private Long id;
    private String name;
    private Media media;

    public CategoryBuilder(){
        this.id=1L;
        this.name="";
        this.media = new MediaBuilder().build();
    }


    @Override
    public CategoryBuilder withId(Long id) {
        this.id=id;
        return this;
    }

    @Override
    public CategoryBuilder withName(String name) {
        this.name=name;
        return this;
    }

    public CategoryBuilder withMedia(Media media){
        this.media = media;
        return this;
    }




    public Category build(){
        Category category = new Category();
        category.setId(this.id);
        category.setName(this.name);
        category.setMedia(this.media);
        return category;
    }
}
