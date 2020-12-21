package com.yp.converter;

import com.yp.dto.CategoryDto;
import com.yp.dto.ProductDto;
import com.yp.entity.Category;
import com.yp.entity.Product;

import java.util.HashSet;
import java.util.Set;

public class ProductConverter {
    public static Product convertToProduct(ProductDto productDto){
        Product p = new Product();
        p.setId(productDto.getId());
        p.setName(productDto.getName());
        p.setDetails(productDto.getDetails());
        p.setMedia(MediaConverter.convertToMedia(productDto.getMedia()));
        p.setPrice(productDto.getPrice());
        Set<Category> categories = new HashSet<>();
        Set<CategoryDto> categoryDtos = productDto.getCategories();
        categoryDtos.forEach(categoryDto -> {
            categories.add(CategoryConverter.convertToCategory(categoryDto));
        });
        p.setCategories(categories);
        return p;
    }
    public static ProductDto convertToProductDto(Product p){
        ProductDto productDto = new ProductDto();
        Set<Category> categories = p.getCategories();
        Set<CategoryDto> categoryDtos = new HashSet<>();
        categories.forEach(category -> {
            categoryDtos.add(CategoryConverter.convertToCategoryDto(category));
        });
        productDto.setCategories(categoryDtos);
        productDto.setId(p.getId());
        productDto.setName(p.getName());
        productDto.setDetails(p.getDetails());
        productDto.setMedia(MediaConverter.convertToMediaDto(p.getMedia()));
        productDto.setPrice(p.getPrice());
        return productDto;
    }
}
