package com.yp.builder;

import com.yp.entity.Media;
import com.yp.entity.TableCategory;

public class TableCategoryBuilder implements Builder{
    private Long id;
    private String name;
    private int number;
    private Media media;

    public TableCategoryBuilder(){
        this.id=1L;
        this.name="";
        this.number=1;
        this.media=new MediaBuilder().build();
    }

    @Override
    public TableCategoryBuilder withId(Long id) {
        this.id=id;
        return this;
    }

    @Override
    public TableCategoryBuilder withName(String name) {
        this.name=name;
        return this;
    }

    public TableCategoryBuilder withNumber(int number) {
        this.number=number;
        return this;
    }

    public TableCategoryBuilder withMedia(Media media){
        this.media=media;
        return this;
    }

    public TableCategory build(){
        TableCategory tableCategory = new TableCategory();
        tableCategory.setId(this.id);
        tableCategory.setName(this.name);
        tableCategory.setNumber(this.number);
        tableCategory.setMedia(this.media);
        return tableCategory;
    }
}
