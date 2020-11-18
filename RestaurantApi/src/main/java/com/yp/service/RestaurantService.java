package com.yp.service;

import com.google.gson.Gson;
import com.yp.model.Product;
import com.yp.repos.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Service
public class RestaurantService {

    @Autowired
    private ProductRepository productRepository;

    public String getAllProducts(){
        List<Product> products = productRepository.findAll();
        return new Gson().toJson(products);
    }


    public Product getProduct(int id){
        Product product = productRepository.findById(id).get();
        return product;
    }

    public void addProduct(Product product){
        productRepository.save(product);
    }

    public void editProduct(int id, Product product){
        deleteProduct(id);
        productRepository.save(product);
    }

    public void deleteProduct(int id){
        productRepository.deleteById(id);

    }



}
