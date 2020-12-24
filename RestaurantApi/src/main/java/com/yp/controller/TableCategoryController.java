package com.yp.controller;

import com.google.gson.Gson;
import com.yp.dto.TableCategoryDto;
import com.yp.entity.TableCategory;
import com.yp.service.TableCategoryService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@Api(tags = "TableCategory Controller")
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/table_category")
public class TableCategoryController {
    @Autowired
    private TableCategoryService tableCategoryService;
    @GetMapping("/{id}")
    public TableCategoryDto getTableCat(@PathVariable(value = "id") Long id){
        return tableCategoryService.getTableCategory(id);
    }

    @GetMapping("/list")
    public List<TableCategoryDto> getCats(){
        return tableCategoryService.getAllTableCategories();
    }

    @GetMapping("/{id}/name")
    public String getCatName(@PathVariable(value = "id") Long id){
        return tableCategoryService.getTableCategory(id).getName();
    }

    @PostMapping("/add")
    public void addTableCat(@RequestBody TableCategoryDto table){
        tableCategoryService.addTableCategory(table);
    }

    @PutMapping("/{id}/put")
    public void editTableCat(@PathVariable(value = "id") Long id, @RequestBody TableCategoryDto tableCat){
        tableCategoryService.editTableCategory(id, tableCat);
    }
    @DeleteMapping("/{id}/delete")
    public void deleteTableCat(@PathVariable(value = "id") Long id){
        tableCategoryService.deleteTableCategory(id);
    }

}
