package com.yp.controller;

import com.yp.model.Product;
import com.yp.service.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
public class RestaurantController {

    @Autowired
    private RestaurantService restaurantService;

    @GetMapping("/list")
    public String getAllProducts(){

        return restaurantService.getAllProducts();
    }

    @GetMapping("/{id}")
    public Product getProduct(@PathVariable(value = "id") int id){

        return restaurantService.getProduct(id);
    }
    @PostMapping("/add")
    public void addProduct(@RequestBody Product product){
        restaurantService.addProduct(product);
    }
    @PutMapping("/{id}")
    public void editProduct(@PathVariable(value = "id") int id, @RequestBody Product product){
        restaurantService.editProduct(id, product);
    }
    @DeleteMapping("/{id}")
    public void deleteProduct(@PathVariable(value = "id") int id){
        restaurantService.deleteProduct(id);
    }


}
