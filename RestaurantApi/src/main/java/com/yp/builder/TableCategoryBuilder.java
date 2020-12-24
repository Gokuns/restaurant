package com.yp.builder;

import com.yp.entity.TableCategory;

public class TableCategoryBuilder extends Builder{
    private Long _id;
    private String _name;
    private int _number;

    public TableCategoryBuilder(){
        this._id=1L;
        this._name="";
        this._number=1;
    }

    @Override
    public TableCategoryBuilder withId(Long id) {
        this._id=id;
        return this;
    }

    @Override
    public TableCategoryBuilder withName(String name) {
        this._name=name;
        return this;
    }

    public TableCategoryBuilder withNumber(int number) {
        this._number=number;
        return this;
    }

    public TableCategory build(){
        TableCategory tableCategory = new TableCategory();
        tableCategory.setId(this._id);
        tableCategory.setName(this._name);
        tableCategory.setNumber(this._number);
        return tableCategory;
    }
}
