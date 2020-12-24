package com.yp.controller;

import com.google.gson.Gson;
import com.yp.dto.CategoryDto;
import com.yp.dto.ProductDto;
import com.yp.entity.Category;
import com.yp.entity.Product;
import com.yp.service.CategoryService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(tags = "Category Controller")
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/category")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @GetMapping("/{id}")
    public CategoryDto getCat(@PathVariable(value = "id") Long id){
        return categoryService.getCategory(id);
    }
    @GetMapping("/list")
    public List<CategoryDto> getAllCats(){
        return categoryService.getAllCategory();
    }
    @GetMapping("/{id}/name")
    public String getCatName(@PathVariable(value = "id") Long id){
        return categoryService.getCategory(id).getName();
    }
    @PostMapping("/add")
    public void addCategory(@RequestBody CategoryDto categoryDto){
       categoryService.addCategory(categoryDto);
    }
    @PutMapping("/{id}/put")
    public void editCategory(@PathVariable(value = "id") Long id, @RequestBody CategoryDto categoryDto){
        categoryService.editCategory(id, categoryDto);
    }
    @DeleteMapping("/{id}/delete")
    public void deleteCategory(@PathVariable(value = "id") Long id){
        categoryService.deleteCategory(id);
    }

    @GetMapping("/list/{id}")
    public List<ProductDto> getProductsofCategory(@PathVariable(value = "id")Long id){
        return categoryService.getProductsWithId(id);
    }





}
