package com.yp.mapper;

import com.yp.dto.ProductDto;
import com.yp.entity.Product;
import org.mapstruct.Context;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import java.util.List;

@Mapper(componentModel="spring")
public interface ProductMapper {

    @Mapping(
            source = "categories",target = "categories")
    @Mapping(source = "media", target = "media")
    ProductDto toDto(Product product);

    List<ProductDto> toDtoList(List<Product> productList);

    List<Product> toEntitiyList(List<ProductDto> productDtoList);

    @Mapping(
            source = "categories",target = "categories")
    @Mapping(source = "media", target = "media")
    @InheritInverseConfiguration
    Product toEntity(ProductDto productDto);


}
