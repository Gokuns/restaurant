package com.yp.service;

import com.yp.converter.ProductConverter;
import com.yp.dto.ProductDto;
import com.yp.entity.Category;
import com.yp.entity.Product;
import com.yp.mapper.ProductMapper;
import com.yp.repos.CategoryRepository;
import com.yp.repos.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private ProductMapper productMapper;



    public List<ProductDto> getAllProducts(){
        List<Product> products = productRepository.findAll();
        List<ProductDto> productDtos = new ArrayList<>();

        products.forEach(product -> {
            ProductDto productDto = productMapper.toDto(product);
            productDtos.add(productDto);
        });
        return productDtos;
    }



    public ProductDto getProduct(int id){
        Product p = productRepository.findById(id).get();
        return  ProductConverter.convertToProductDto(p);
    }

    public Product addProduct(ProductDto product){
        Product p = productMapper.toEntity(product);
        Set<Category> categories = new HashSet<>();
        p.getCategories().forEach(category -> {
            Category cat = categoryRepository.findById(category.getId()).orElse(null);
            categories.add(cat);
        });
        p.setCategories(categories);
        return productRepository.save(p);
    }

    public Product editProduct(int id, ProductDto product){
        Product p = productRepository.findById(id).get();
        Product newProduct = productMapper.toEntity(product);
        p.setName(newProduct.getName());
        p.setDetails(newProduct.getDetails());
        p.setMedia(newProduct.getMedia());
        p.setPrice(newProduct.getPrice());
        p.setCategories(newProduct.getCategories());
        return productRepository.save(p);
    }

    public Slice<ProductDto> getProductSlices(Pageable pageable){
        Slice<ProductDto> productSlice = productRepository.findAllSlice(pageable).map(productMapper::toDto);
        return productSlice;
    }

    public Slice<ProductDto> getProductSliceWithCategory(int categoryId, Pageable pageable){
        Category cat = categoryRepository.findById(categoryId).get();
        Slice<ProductDto> productSlice = productRepository.findAllByCategories(cat, pageable).map(productMapper::toDto);
        return productSlice;
    }

    public Page<ProductDto> getProductPage(Pageable pageable){
        Page<ProductDto> productSlice = productRepository.findAllPages(pageable).map(productMapper::toDto);
        return productSlice;
    }

    public void deleteProduct(int id){
        productRepository.deleteById(id);
    }



}
