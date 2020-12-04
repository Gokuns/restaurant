package com.yp.controller;

import com.google.gson.Gson;
import com.yp.dto.TableCategoryDto;
import com.yp.entity.TableCategory;
import com.yp.service.TableCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/table_category")
public class TableCategoryController {
    @Autowired
    private TableCategoryService tableCategoryService;

    @GetMapping("/{id}")
    public TableCategoryDto asd(@PathVariable(value = "id") int id){
        return tableCategoryService.getTableCategory(id);
    }

    @GetMapping("/list")
    public List<TableCategoryDto> categories(){
        return tableCategoryService.getAllTableCategories();
    }

    @GetMapping("/{id}/name")
    public String getCatName(@PathVariable(value = "id") int id){
        return tableCategoryService.getTableCategory(id).getName();
    }

    @PostMapping("/add")
    public void addTableCat(@RequestBody TableCategoryDto table){
        tableCategoryService.addTableCategory(table);
    }

    @PutMapping("/{id}/put")
    public void editProduct(@PathVariable(value = "id") int id, @RequestBody TableCategoryDto tableCat){
        tableCategoryService.editTableCategory(id, tableCat);
    }
    @DeleteMapping("/{id}/delete")
    public void deleteProduct(@PathVariable(value = "id") int id){
        tableCategoryService.deleteTableCategory(id);
    }

}
