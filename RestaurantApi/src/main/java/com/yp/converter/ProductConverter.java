package com.yp.converter;

import com.yp.dto.CategoryDto;
import com.yp.dto.ProductDto;
import com.yp.entity.Category;
import com.yp.entity.Product;

public class ProductConverter {
    public static Product convertToProduct(ProductDto productDto){
        Product p = new Product();
        p.setId(productDto.getId());
        p.setName(productDto.getName());
        p.setDetails(productDto.getDetails());
        p.setImg(productDto.getImg());
        p.setPrice(productDto.getPrice());
        Category category = CategoryConverter.convertToCategory(productDto.getCategory());
        p.setCategory(category);
        return p;
    }
    public static ProductDto convertToProductDto(Product p){
        ProductDto productDto = new ProductDto();
        CategoryDto categoryDto = CategoryConverter.convertToCategoryDto(p.getCategory());
        productDto.setCategory(categoryDto);
        productDto.setId(p.getId());
        productDto.setName(p.getName());
        productDto.setDetails(p.getDetails());
        productDto.setImg(p.getImg());
        productDto.setPrice(p.getPrice());
        return productDto;
    }
}
