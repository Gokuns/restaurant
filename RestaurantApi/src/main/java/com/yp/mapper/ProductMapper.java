package com.yp.mapper;

import com.yp.dto.ProductDto;
import com.yp.entity.Product;
import org.mapstruct.Mapper;

@Mapper
public interface ProductMapper {

    ProductDto toDto(Product product);

    Product toEntity(ProductDto productDto);
}
