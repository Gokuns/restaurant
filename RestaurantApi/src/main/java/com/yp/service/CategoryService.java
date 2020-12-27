package com.yp.service;


import com.yp.dto.CategoryDto;
import com.yp.dto.ProductDto;
import com.yp.entity.Category;
import com.yp.entity.Product;
import com.yp.exception.BusinessRuleException;
import com.yp.exception.ContentNotFoundException;
import com.yp.mapper.CategoryMapper;
import com.yp.mapper.MediaMapper;
import com.yp.mapper.ProductMapper;
import com.yp.repos.CategoryRepository;
import com.yp.repos.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;

import java.util.*;


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

    @Cacheable(value = "CategoryCache")
    public List<CategoryDto> getAllCategory(String lang){
        List<Category> categories =  categoryRepository.findAll();
        return categoryMapper.toDtoList(categories);
    }

    @Cacheable(value = "CategoryCache",key = "'CATEGORY_CACHE_BY_ID'.concat(#id)")
    public CategoryDto getCategory(Long id){
        if(id==null) throw new BusinessRuleException("No Id was sent");
        Optional<Category> optionalCategory = categoryRepository.findById(id);
        if(optionalCategory.isEmpty()) throw new ContentNotFoundException("Category not found");
        return categoryMapper.toDto(optionalCategory.get());

    }

    @CacheEvict(value = "CategoryCache", allEntries = true)
    public Category addCategory(CategoryDto categoryDto){
        if(categoryDto==null) throw new BusinessRuleException("Category cannot be null");
        Category cat = categoryMapper.toEntity(categoryDto);
        return categoryRepository.save(cat);
    }

    //@CacheEvict(value = "CategoryCache", key = "'CATEGORY_CACHE_BY_ID'.concat(#id)")
    @CacheEvict(value = "CategoryCache", allEntries = true)
    public Category editCategory(Long id, CategoryDto categoryDto){
        Optional<Category> optionalCategory = categoryRepository.findById(id);
        if(optionalCategory.isEmpty()) {
            throw new ContentNotFoundException("Category not found");
        }
        Category cat = optionalCategory.get();
        if(!cat.getName().equals(categoryDto.getName())){
            cat.setName(categoryDto.getName());
        }
        if(cat.getMedia()!=mediaMapper.toEntity(categoryDto.getMedia())){
            cat.setMedia(mediaMapper.toEntity(categoryDto.getMedia()));
        }
        return categoryRepository.save(cat);
    }

    @CacheEvict(value = "CategoryCache", allEntries = true)
    public void deleteCategory(Long id){
        if(id==null) throw new BusinessRuleException("No id was given");
        categoryRepository.deleteById(id);
    }

    public List<ProductDto> getProductsWithCategoryId(Long id){
        Optional<Category> optionalCategory = categoryRepository.findById(id);
        if(optionalCategory.isEmpty()) {
            return null;
        }
        Category category = optionalCategory.get();
        List<Product> products = productRepository.findAllByCategoriesIn(Collections.singletonList(category));
        return productMapper.toDtoList(products);
    }

    public Slice<ProductDto> getProductsSliceWithCategoryId(Long id, Pageable pageable){
        Optional<Category> optionalCategory = categoryRepository.findById(id);
        if(optionalCategory.isEmpty()){
            return null;
        }
        Category category = optionalCategory.get();
        return productRepository.findAllByCategories(category, pageable).map(productMapper::toDto);
    }

}
