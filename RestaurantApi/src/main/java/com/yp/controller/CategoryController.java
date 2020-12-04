package com.yp.controller;

import com.google.gson.Gson;
import com.yp.dto.CategoryDto;
import com.yp.dto.ProductDto;
import com.yp.entity.Category;
import com.yp.entity.Product;
import com.yp.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/category")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @GetMapping("/{id}")
    public CategoryDto asd(@PathVariable(value = "id") int id){

        return categoryService.getCategory(id);
    }

    @GetMapping("/list")
    public List<CategoryDto> categories(){
        return categoryService.getAllCategory();
    }

    @GetMapping("/{id}/name")
    public String getCatName(@PathVariable(value = "id") int id){

        return categoryService.getCategory(id).getName();
    }

    @PostMapping("/add/")
    public void addProduct(@RequestBody CategoryDto categoryDto){
       categoryService.addCategory(categoryDto);
    }

    @PutMapping("/{id}/put/")
    public void editProduct(@PathVariable(value = "id") int id, @RequestBody CategoryDto categoryDto){
        categoryService.editCategory(id, categoryDto);
    }
    @DeleteMapping("/{id}/delete")
    public void deleteProduct(@PathVariable(value = "id") int id){
        categoryService.deleteCategory(id);
    }

    @GetMapping("/list/{id}")
    public List<ProductDto> getProductsofCategory(@PathVariable(value = "id")int id){
        return categoryService.getProductsWithId(id);
    }


}
