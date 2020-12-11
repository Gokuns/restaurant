package com.yp.builder;

import com.yp.dto.TableCategoryDto;
import com.yp.entity.TableCategory;

public class TableCategoryDtoBuilder extends Builder{
    private int _id;
    private String _name;
    private int _number;

    public TableCategoryDtoBuilder(){
        this._id=1;
        this._name="";
        this._number=1;
    }

    @Override
    public TableCategoryDtoBuilder withId(int id) {
        this._id=id;
        return this;
    }

    @Override
    public TableCategoryDtoBuilder withName(String name) {
        this._name=name;
        return this;
    }

    public TableCategoryDtoBuilder withNumber(int number) {
        this._number=number;
        return this;
    }

    public TableCategoryDto build(){
        TableCategoryDto tableCategory = new TableCategoryDto();
        tableCategory.setId(this._id);
        tableCategory.setName(this._name);
        tableCategory.setNumber(this._number);
        return tableCategory;
    }
}
