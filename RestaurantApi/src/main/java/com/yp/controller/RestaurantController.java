package com.yp.controller;

import com.google.gson.Gson;
import com.yp.model.Product;
import com.yp.service.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@CrossOrigin(origins = "*")
@RestController
public class RestaurantController {

    @Autowired
    private RestaurantService restaurantService;

    @GetMapping("/list")
    public String getAllProducts(){

        return new Gson().toJson(restaurantService.getAllProducts());
    }
    @GetMapping("/view/{category}")
    public String asd(@PathVariable(value = "category") String category){

        return new Gson().toJson(restaurantService.getAllWithCategory(category));
    }

    @GetMapping("/categories")
    public String categories(){

        return new Gson().toJson(restaurantService.getDistinctCategory());
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
