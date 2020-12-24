package com.yp.service;


import com.yp.dto.CategoryDto;
import com.yp.dto.ProductDto;
import com.yp.entity.Category;
import com.yp.entity.Product;
import com.yp.mapper.CategoryMapper;
import com.yp.mapper.MediaMapper;
import com.yp.mapper.ProductMapper;
import com.yp.repos.CategoryRepository;
import com.yp.repos.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private CategoryMapper categoryMapper;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProductMapper productMapper;

    @Autowired
    private MediaMapper mediaMapper;

    public List<CategoryDto> getAllCategory(){
        List<Category> categories =  categoryRepository.findAll();
        List<CategoryDto> categoryDtos = new ArrayList<>();
        categories.forEach(category -> {
            CategoryDto categoryDto = categoryMapper.toDto(category);
            categoryDtos.add(categoryDto);
        });
        return categoryDtos;
    }

    public CategoryDto getCategory(Long id){
        Category category = categoryRepository.findById(id).get();
        CategoryDto categoryDto = categoryMapper.toDto(category);
        return categoryDto;
    }

    public Category addCategory(CategoryDto categoryDto){
        Category cat = categoryMapper.toEntity(categoryDto);
        return categoryRepository.save(cat);
    }

    public Category editCategory(Long id, CategoryDto categoryDto){
        Category cat = categoryRepository.findById(id).get();
        cat.setName(categoryDto.getName());
        cat.setMedia(mediaMapper.toEntity(categoryDto.getMedia()));
        return categoryRepository.save(cat);
    }

    public void deleteCategory(Long id){
        categoryRepository.deleteById(id);
    }

    public List<ProductDto> getProductsWithId(Long id){
        Category category = categoryRepository.findById(id).get();
        List<Product> products = productRepository.findAllByCategoriesIn(Arrays.asList(category));
        List<ProductDto> productDtos = new ArrayList<>();
        products.forEach(product -> {
            ProductDto productDto = productMapper.toDto(product);
            productDtos.add(productDto);
        });
        return productDtos;
    }

    public Slice<ProductDto> getProductSliceWithId(Long id, Pageable pageable){
        Category category = categoryRepository.findById(id).get();
        Slice<ProductDto> productSlice = productRepository.findAllByCategories(category, pageable).map(productMapper::toDto);
        return productSlice;
    }

}
