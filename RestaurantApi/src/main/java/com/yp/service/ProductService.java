package com.yp.service;

import com.yp.dto.ProductDto;
import com.yp.entity.Category;
import com.yp.entity.Product;
import com.yp.exception.BusinessRuleException;
import com.yp.exception.ContentNotFoundException;
import com.yp.mapper.ProductMapper;
import com.yp.repos.CategoryRepository;
import com.yp.repos.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private ProductMapper productMapper;
    @Autowired
    private MessageSource messageSource;
    private static final String BUSINESS_RULE_EXCEPTION = "BusinessRuleException";
    private static final String CONTENT_NOT_FOUND = "ContentNotFound";



    public List<ProductDto> getAllProducts(){
        return productRepository.findAll().stream().map(productMapper::toDto).collect(Collectors.toList());
    }



    public ProductDto getProduct(Long id, String lang){
        if(id==null){
            throw new BusinessRuleException(messageSource.getMessage(BUSINESS_RULE_EXCEPTION, new Object[0], new Locale(lang)));
        }
        Optional<Product> optionalProduct = productRepository.findById(id);
        if(optionalProduct.isEmpty()){
            throw new ContentNotFoundException(messageSource.getMessage(CONTENT_NOT_FOUND, new Object[0], new Locale(lang)));
        }
        return  productMapper.toDto(optionalProduct.get());
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public Product addProduct(ProductDto product, String lang){
        if(product==null){
            throw new BusinessRuleException(messageSource.getMessage(CONTENT_NOT_FOUND, new Object[0], new Locale(lang)));
        }
        Product p = productMapper.toEntity(product);
        return productRepository.save(p);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public Product editProduct(Long id, ProductDto product, String lang){
        if(id==null){
            throw new BusinessRuleException(messageSource.getMessage(BUSINESS_RULE_EXCEPTION, new Object[0], new Locale(lang)));
        }
        if(product==null){
            throw new BusinessRuleException(messageSource.getMessage(BUSINESS_RULE_EXCEPTION, new Object[0], new Locale(lang)));
        }
        Optional<Product> optionalProduct = productRepository.findById(id);
        if(optionalProduct.isEmpty()){
            throw new ContentNotFoundException(messageSource.getMessage(CONTENT_NOT_FOUND, new Object[0], new Locale(lang)));
        }
        Product p = optionalProduct.get();
        Product newProduct = productMapper.toEntity(product);
        if(!p.getName().equals(newProduct.getName())){
            p.setName(newProduct.getName());
        }
        if(!p.getDetails().equals(newProduct.getDetails())){
            p.setDetails(newProduct.getDetails());
        }
        if(!p.getMedia().equals(newProduct.getMedia())){
            p.setMedia(newProduct.getMedia());
        }
        if(p.getPrice()!=newProduct.getPrice()){
            p.setPrice(newProduct.getPrice());
        }
        if(!p.getCategories().equals(newProduct.getCategories())){
            p.setCategories(newProduct.getCategories());
        }
        return productRepository.save(p);
    }

    public Slice<ProductDto> getProductSlices(Pageable pageable, String lang){
        if(pageable==null){
            throw new BusinessRuleException(messageSource.getMessage(BUSINESS_RULE_EXCEPTION, new Object[0], new Locale(lang)));
        }
        return productRepository.findAllSlice(pageable).map(productMapper::toDto);
    }

    public Slice<ProductDto> getProductSliceWithCategory(Long categoryId, Pageable pageable, String lang){
        if(categoryId==null){
            throw new BusinessRuleException(messageSource.getMessage(BUSINESS_RULE_EXCEPTION, new Object[0], new Locale(lang)));
        }
        Optional<Category> optionalCategory = categoryRepository.findById(categoryId);
        if(optionalCategory.isEmpty()){
            throw new ContentNotFoundException(messageSource.getMessage(CONTENT_NOT_FOUND, new Object[0], new Locale(lang)));
        }
        Category cat = optionalCategory.get();
        return  productRepository.findAllByCategories(cat, pageable).map(productMapper::toDto);

    }

    public Page<ProductDto> getProductPage(Pageable pageable, String lang){
        if(pageable==null){
            throw new BusinessRuleException(messageSource.getMessage(BUSINESS_RULE_EXCEPTION, new Object[0], new Locale(lang)));
        }
        return productRepository.findAllPages(pageable).map(productMapper::toDto);

    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void deleteProduct(Long id, String lang){
        if(id==null){
            throw new BusinessRuleException(messageSource.getMessage(BUSINESS_RULE_EXCEPTION, new Object[0], new Locale(lang)));
        }
        productRepository.deleteById(id);
    }



}
