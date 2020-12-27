package com.yp.mapper;

import com.yp.dto.CategoryDto;
import com.yp.entity.Category;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import java.util.List;

@Mapper(componentModel="spring")
public interface CategoryMapper {


    @Mapping(source = "media", target = "media")
    CategoryDto toDto(Category category);

    List<CategoryDto> toDtoList(List<Category> categoryList);

    List<Category> toEntityList(List<CategoryDto> categoryDtoList);

    @Mapping(source = "media", target = "media")
    Category toEntity(CategoryDto categoryDto);


}
