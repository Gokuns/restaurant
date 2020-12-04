package com.yp.converter;

import com.yp.dto.CategoryDto;
import com.yp.entity.Category;

public class CategoryConverter {
    public static CategoryDto convertToCategoryDto(Category category){
        CategoryDto categoryDto = new CategoryDto();
        categoryDto.setName(category.getName());
        categoryDto.setId(category.getId());
        return categoryDto;
    }
    public static Category convertToCategory(CategoryDto categoryDto){
        Category cat = new Category();
        cat.setName(categoryDto.getName());
        return cat;
    }
}
