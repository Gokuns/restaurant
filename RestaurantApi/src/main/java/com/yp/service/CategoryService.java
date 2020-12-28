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
import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.MessageSource;
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

    @Autowired
    private MessageSource messageSource;
    private static final String BUSINESS_RULE_EXCEPTION = "BusinessRuleException";
    private static final String CONTENT_NOT_FOUND = "ContentNotFound";

    @Cacheable(value = "CategoryCache")
    public List<CategoryDto> getAllCategory(){
        List<Category> categories =  categoryRepository.findAll();
        return categoryMapper.toDtoList(categories);
    }

    @Cacheable(value = "CategoryCache",key = "'CATEGORY_CACHE_BY_ID'.concat(#id)")
    public CategoryDto getCategory(Long id, String lang){
        if(id==null) throw new BusinessRuleException(messageSource.getMessage(BUSINESS_RULE_EXCEPTION, new Object[0], new Locale(lang)));
        Optional<Category> optionalCategory = categoryRepository.findById(id);
        if(optionalCategory.isEmpty()) throw new ContentNotFoundException(messageSource.getMessage(CONTENT_NOT_FOUND, new Object[0], new Locale(lang)));
        return categoryMapper.toDto(optionalCategory.get());

    }

    @CacheEvict(value = "CategoryCache", allEntries = true)
    public Category addCategory(CategoryDto categoryDto, String lang){
        if(categoryDto==null) throw new BusinessRuleException(messageSource.getMessage(BUSINESS_RULE_EXCEPTION, new Object[0], new Locale(lang)));
        Category cat = categoryMapper.toEntity(categoryDto);
        return categoryRepository.save(cat);
    }

    //@CacheEvict(value = "CategoryCache", key = "'CATEGORY_CACHE_BY_ID'.concat(#id)")
    @CacheEvict(value = "CategoryCache", allEntries = true)
    public Category editCategory(Long id, CategoryDto categoryDto, String lang){
        Optional<Category> optionalCategory = categoryRepository.findById(id);
        if(optionalCategory.isEmpty()) {
            throw new ContentNotFoundException(messageSource.getMessage(CONTENT_NOT_FOUND, new Object[0], new Locale(lang)));
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
    public void deleteCategory(Long id, String lang){
        if(id==null) throw new BusinessRuleException(messageSource.getMessage(BUSINESS_RULE_EXCEPTION, new Object[0], new Locale(lang)));
        categoryRepository.deleteById(id);
    }

    public List<ProductDto> getProductsWithCategoryId(Long id, String lang){
        if(id==null){
            throw new BusinessRuleException(messageSource.getMessage(BUSINESS_RULE_EXCEPTION, new Object[0], new Locale(lang)));
        }
        Optional<Category> optionalCategory = categoryRepository.findById(id);
        if(optionalCategory.isEmpty()) {
            throw new ContentNotFoundException(messageSource.getMessage(CONTENT_NOT_FOUND, new Object[0], new Locale(lang)));
        }
        Category category = optionalCategory.get();
        List<Product> products = productRepository.findAllByCategoriesIn(Collections.singletonList(category));
        return productMapper.toDtoList(products);
    }

    public Slice<ProductDto> getProductsSliceWithCategoryId(Long id, Pageable pageable, String lang){
        Optional<Category> optionalCategory = categoryRepository.findById(id);
        if(optionalCategory.isEmpty()){
            throw new ContentNotFoundException(messageSource.getMessage(CONTENT_NOT_FOUND, new Object[0], new Locale(lang)));
        }
        Category category = optionalCategory.get();
        return productRepository.findAllByCategories(category, pageable).map(productMapper::toDto);
    }

}
