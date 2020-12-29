package com.yp.builder;

import com.yp.dto.MediaDto;
import com.yp.dto.TableCategoryDto;

public class TableCategoryDtoBuilder implements Builder{
    private Long id;
    private String name;
    private int number;
    private MediaDto media;

    public TableCategoryDtoBuilder(){
        this.id=1L;
        this.name="";
        this.number=1;
        this.media= new MediaDtoBuilder().build();
    }

    @Override
    public TableCategoryDtoBuilder withId(Long id) {
        this.id=id;
        return this;
    }

    @Override
    public TableCategoryDtoBuilder withName(String name) {
        this.name=name;
        return this;
    }

    public TableCategoryDtoBuilder withNumber(int number) {
        this.number=number;
        return this;
    }

    public TableCategoryDtoBuilder withMedia(MediaDto media){
        this.media = media;
        return this;
    }

    public TableCategoryDto build(){
        TableCategoryDto tableCategory = new TableCategoryDto();
        tableCategory.setId(this.id);
        tableCategory.setName(this.name);
        tableCategory.setNumber(this.number);
        tableCategory.setMedia(this.media);
        return tableCategory;
    }
}
