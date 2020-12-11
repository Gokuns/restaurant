package com.yp.service;

import com.yp.converter.ProductConverter;
import com.yp.dto.ProductDto;
import com.yp.entity.Category;
import com.yp.entity.Product;
import com.yp.repos.CategoryRepository;
import com.yp.repos.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private CategoryRepository categoryRepository;



    public List<ProductDto> getAllProducts(){
        List<Product> products = productRepository.findAll();
        List<ProductDto> productDtos = new ArrayList<>();

        products.forEach(product -> {
            ProductDto productDto = ProductConverter.convertToProductDto(product);
            productDtos.add(productDto);
        });
        return productDtos;
    }



    public ProductDto getProduct(int id){
        Product p = productRepository.findById(id).get();
        return  ProductConverter.convertToProductDto(p);
    }

    public Product addProduct(ProductDto product){
        Product p = ProductConverter.convertToProduct(product);
        p.getCategories().forEach(category -> {
            Category cat = categoryRepository.findById(category.getId()).orElse(null);
            cat.getProducts().add(p);
        });
        return productRepository.save(p);
    }

    public Product editProduct(int id, ProductDto product){
        Product p = productRepository.findById(id).get();
        Product newProduct = ProductConverter.convertToProduct(product);
        p.setName(newProduct.getName());
        p.setDetails(newProduct.getDetails());
        p.setImg(newProduct.getImg());
        p.setPrice(newProduct.getPrice());
        p.setCategories(newProduct.getCategories());
        return productRepository.save(p);
    }

    public void deleteProduct(int id){
        productRepository.deleteById(id);
    }



}
