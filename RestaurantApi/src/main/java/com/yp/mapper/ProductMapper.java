package com.yp.mapper;

import com.yp.dto.ProductDto;
import com.yp.entity.Product;
import org.mapstruct.Context;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel="spring")
public interface ProductMapper {

    @Mapping(
            source = "categories",target = "categories")
    @Mapping(source = "media", target = "media")
    ProductDto toDto(Product product);

    @Mapping(
            source = "categories",target = "categories")
    @Mapping(source = "media", target = "media")
    @InheritInverseConfiguration
    Product toEntity(ProductDto productDto);


}
