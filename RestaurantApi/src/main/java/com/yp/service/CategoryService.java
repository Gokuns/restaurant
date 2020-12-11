package com.yp.service;

import com.yp.converter.CategoryConverter;
import com.yp.converter.MediaConverter;
import com.yp.converter.ProductConverter;
import com.yp.dto.CategoryDto;
import com.yp.dto.ProductDto;
import com.yp.entity.Category;
import com.yp.entity.Product;
import com.yp.repos.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
public class CategoryService {
    @Autowired
    CategoryRepository categoryRepository;

    public List<CategoryDto> getAllCategory(){
        List<Category> categories =  categoryRepository.findAll();
        List<CategoryDto> categoryDtos = new ArrayList<>();
        categories.forEach(category -> {
            CategoryDto categoryDto = CategoryConverter.convertToCategoryDto(category);
            categoryDtos.add(categoryDto);
        });
        return categoryDtos;
    }

    public CategoryDto getCategory(int id){
        Category category = categoryRepository.findById(id).get();
        CategoryDto categoryDto = CategoryConverter.convertToCategoryDto(category);
        return categoryDto;
    }

    public Category addCategory(CategoryDto categoryDto){
        Category cat = CategoryConverter.convertToCategory(categoryDto);
        return categoryRepository.save(cat);
    }

    public Category editCategory(int id, CategoryDto categoryDto){
        Category cat = categoryRepository.findById(id).get();
        cat.setName(categoryDto.getName());
        cat.setMedia(MediaConverter.convertToMedia(categoryDto.getMedia()));
        return categoryRepository.save(cat);
    }

    public void deleteCategory(int id){
        categoryRepository.deleteById(id);
    }

    public List<ProductDto> getProductsWithId(int id){
        Set<Product> products =  categoryRepository.findById(id).get().getProducts();
        List<ProductDto> productDtos = new ArrayList<>();
        products.forEach(product -> {
            ProductDto productDto = ProductConverter.convertToProductDto(product);
            productDtos.add(productDto);
        });
        return productDtos;
    }
}
