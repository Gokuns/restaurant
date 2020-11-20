package com.yp.service;

import com.yp.model.SellOrder;
import com.yp.model.Product;
import com.yp.model.User;
import com.yp.repos.SellOrderRepository;
import com.yp.repos.ProductRepository;
import com.yp.repos.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;



    public List<Product> getAllProducts(){
        List<Product> products = productRepository.findAll();
        return products;
    }

    public List<Product> getAllWithCategory(String category){
        return productRepository.findAllWithCategory(category);
    }

    public List<String> getDistinctCategory(){
        return productRepository.findDistinctCategories();
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
