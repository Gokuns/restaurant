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
        ProductDto productDto = ProductConverter.convertToProductDto(p);
        return productDto;
    }

    public Product addProduct(ProductDto product, int categoryId){
        Category cat = categoryRepository.findById(categoryId).get();
        Product p = ProductConverter.convertToProduct(product);
        p.setCategory(cat);
        cat.getProducts().add(p);
        return productRepository.save(p);
    }

    public Product editProduct(int id, ProductDto product){
        Product p = productRepository.findById(id).get();
        Category cat = categoryRepository.findById(product.getCategory().getId()).get();
        p.setName(product.getName());
        p.setDetails(product.getDetails());
        p.setImg(product.getImg());
        p.setPrice(p.getPrice());
        return productRepository.save(p);
    }

    public void deleteProduct(int id){
        productRepository.deleteById(id);
    }



}
