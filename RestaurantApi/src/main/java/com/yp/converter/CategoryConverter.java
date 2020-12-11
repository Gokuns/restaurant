package com.yp.converter;

import com.yp.dto.CategoryDto;
import com.yp.entity.Category;
import com.yp.entity.Media;

public class CategoryConverter {
    public static CategoryDto convertToCategoryDto(Category category){
        CategoryDto categoryDto = new CategoryDto();
        categoryDto.setName(category.getName());
        categoryDto.setId(category.getId());
        categoryDto.setMedia(MediaConverter.convertToMediaDto(category.getMedia()));
        return categoryDto;
    }
    public static Category convertToCategory(CategoryDto categoryDto){
        Category cat = new Category();
        cat.setId(categoryDto.getId());
        cat.setName(categoryDto.getName());
        cat.setMedia(MediaConverter.convertToMedia(categoryDto.getMedia()));
        return cat;
    }
}
