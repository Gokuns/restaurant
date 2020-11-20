package com.yp.controller;

import com.google.gson.Gson;
import com.yp.model.Product;
import com.yp.model.User;
import com.yp.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@CrossOrigin(origins = "*")
@RestController
public class ProductController {

    @Autowired
    private ProductService productService;


    @GetMapping("/list")
    public String getAllProducts(){
        return new Gson().toJson(productService.getAllProducts());
    }
    @GetMapping("/view/{category}")
    public String asd(@PathVariable(value = "category") String category){

        return new Gson().toJson(productService.getAllWithCategory(category));
    }

    @GetMapping("/categories")
    public String categories(){
        return new Gson().toJson(productService.getDistinctCategory());
    }


    @GetMapping("/{id}")
    public Product getProduct(@PathVariable(value = "id") int id){
        return productService.getProduct(id);
    }
    @PostMapping("/add")
    public void addProduct(@RequestBody Product product){
        productService.addProduct(product);
    }

    @PutMapping("/{id}")
    public void editProduct(@PathVariable(value = "id") int id, @RequestBody Product product){
        productService.editProduct(id, product);
    }
    @DeleteMapping("/{id}")
    public void deleteProduct(@PathVariable(value = "id") int id){
        productService.deleteProduct(id);
    }


}
