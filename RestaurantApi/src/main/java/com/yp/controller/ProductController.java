package com.yp.controller;

import com.google.gson.Gson;
import com.yp.dto.ProductDto;
import com.yp.entity.Product;
import com.yp.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductService productService;


    @GetMapping("/list")
    public List<ProductDto> getAllProducts(){
        return  productService.getAllProducts();
    }

    @GetMapping("/{id}")
    public ProductDto getProduct(@PathVariable(value = "id") int id){
        return productService.getProduct(id);
    }

    @PostMapping("/add/{id}")
    public void addProduct(@PathVariable(value = "id") int categoryId, @RequestBody ProductDto product){
        productService.addProduct(product,categoryId);
    }

    @PutMapping("/{id}/put")
    public void editProduct(@PathVariable(value = "id") int id, @RequestBody ProductDto product){
        productService.editProduct(id, product);
    }
    @DeleteMapping("/{id}/delete")
    public void deleteProduct(@PathVariable(value = "id") int id){
        productService.deleteProduct(id);
    }
}
