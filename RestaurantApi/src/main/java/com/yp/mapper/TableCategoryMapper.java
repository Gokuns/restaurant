package com.yp.mapper;

import com.yp.dto.TableCategoryDto;
import com.yp.entity.TableCategory;
import org.mapstruct.Mapper;

@Mapper(componentModel="spring")
public interface TableCategoryMapper {

    TableCategoryDto toDto(TableCategory tableCategory);

    TableCategory toEntity(TableCategoryDto tableCategoryDto);

}
