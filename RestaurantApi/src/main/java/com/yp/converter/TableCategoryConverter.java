package com.yp.converter;

import com.yp.dto.TableCategoryDto;
import com.yp.entity.TableCategory;

public  class TableCategoryConverter {
    public static TableCategory convertToTableCategory(TableCategoryDto tableCategoryDto){
        TableCategory tableCategory = new TableCategory();
        tableCategory.setName(tableCategoryDto.getName());
        tableCategory.setNumber(tableCategoryDto.getNumber());
        return tableCategory;
    }
    public static TableCategoryDto convertToTableCategoryDto(TableCategory tableCategory){
        TableCategoryDto tableCategoryDto = new TableCategoryDto();
        tableCategoryDto.setId(tableCategory.getId());
        tableCategoryDto.setName(tableCategory.getName());
        tableCategoryDto.setNumber(tableCategory.getNumber());
        return tableCategoryDto;
    }
}
