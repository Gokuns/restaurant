package com.yp.converter;

import com.yp.dto.CategoryDto;
import com.yp.dto.ProductDto;
import com.yp.entity.Product;

public class ProductConverter {
    public static Product convertToProduct(ProductDto productDto){
        Product p = new Product();
        p.setId(productDto.getId());
        p.setName(productDto.getName());
        p.setDetails(productDto.getDetails());
        p.setImg(productDto.getImg());
        p.setPrice(productDto.getPrice());
        return p;
    }
    public static ProductDto convertToProductDto(Product p){
        ProductDto productDto = new ProductDto();
        CategoryDto categoryDto = new CategoryDto();
        categoryDto.setId(p.getCategory().getId());
        categoryDto.setName(p.getCategory().getName());
        productDto.setCategory(categoryDto);
        productDto.setId(p.getId());
        productDto.setName(p.getName());
        productDto.setDetails(p.getDetails());
        productDto.setImg(p.getImg());
        productDto.setPrice(p.getPrice());
        return productDto;
    }
}
