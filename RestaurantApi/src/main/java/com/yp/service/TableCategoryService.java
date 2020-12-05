package com.yp.service;

import com.yp.converter.TableCategoryConverter;
import com.yp.dto.TableCategoryDto;
import com.yp.entity.TableCategory;
import com.yp.repos.TableCategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TableCategoryService {
    @Autowired
    private TableCategoryRepository tableCategoryRepository;

    public List<TableCategoryDto> getAllTableCategories(){
        List<TableCategory> tableCategories = tableCategoryRepository.findAll();
        List<TableCategoryDto> tableCategoryDtos = new ArrayList<>();
        tableCategories.forEach(tableCategory -> {
            TableCategoryDto tableCategoryDto = TableCategoryConverter.convertToTableCategoryDto(tableCategory);
            tableCategoryDtos.add(tableCategoryDto);
        });
        return tableCategoryDtos;
    }

    public TableCategoryDto getTableCategory(int id){
        TableCategory tableCategory =  getTabCat(id);
        TableCategoryDto tableCategoryDto = TableCategoryConverter.convertToTableCategoryDto(tableCategory);
        return tableCategoryDto;
    }

    public TableCategory addTableCategory(TableCategoryDto tableCat){
        TableCategory tableCategory = TableCategoryConverter.convertToTableCategory(tableCat);
        return tableCategoryRepository.save(tableCategory);
    }

    public TableCategory editTableCategory(int id, TableCategoryDto tabCat){
        TableCategory cat = getTabCat(id);
        cat.setName(tabCat.getName());
        cat.setNumber(tabCat.getNumber());
        return tableCategoryRepository.save(cat);
    }

    public void deleteTableCategory(int id){
        tableCategoryRepository.deleteById(id);
    }


    private TableCategory getTabCat(int id){
        Optional<TableCategory> opt =tableCategoryRepository.findById(id);
        return opt.orElse(null);
    }

}

