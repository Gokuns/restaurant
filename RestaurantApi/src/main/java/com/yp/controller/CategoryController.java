package com.yp.controller;

import com.yp.dto.CategoryDto;
import com.yp.dto.ProductDto;
import com.yp.service.CategoryService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Api(tags = "Category Controller")
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/categories")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN') || hasAuthority('USER')")
    public CategoryDto getCat(@PathVariable(value = "id") Long id, @RequestParam(value = "lang", defaultValue = "en") String lang){
        return categoryService.getCategory(id, lang);
    }
    @GetMapping
    @PreAuthorize("hasAuthority('ADMIN') || hasAuthority('USER')")
    public List<CategoryDto> getAllCats(){
        return categoryService.getAllCategory();
    }

    @PostMapping
    @PreAuthorize("hasAuthority('ADMIN')")
    public void addCategory(@Valid  @RequestBody CategoryDto categoryDto, @RequestParam(value = "lang", defaultValue = "en") String lang){
       categoryService.addCategory(categoryDto, lang);
    }
    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public void editCategory(@PathVariable(value = "id") Long id,@Valid  @RequestBody CategoryDto categoryDto,@RequestParam(value = "lang", defaultValue = "en")String lang){
        categoryService.editCategory(id, categoryDto, lang);
    }
    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public void deleteCategory(@PathVariable(value = "id") Long id, @RequestParam(value = "lang", defaultValue = "en")String lang){
        categoryService.deleteCategory(id, lang);
    }


    @GetMapping("/{id}/list")
    @PreAuthorize("hasAuthority('ADMIN') || hasAuthority('USER')")
    public List<ProductDto> getProductsofCategory(@PathVariable(value = "id")Long id, @RequestParam(value = "lang", defaultValue = "en") String lang){
        return categoryService.getProductsWithCategoryId(id, lang);
    }





}
