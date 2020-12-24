package com.yp.service;

import com.yp.dto.TableCategoryDto;
import com.yp.entity.TableCategory;
import com.yp.mapper.TableCategoryMapper;
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
    @Autowired
    private TableCategoryMapper tableCategoryMapper;

    public List<TableCategoryDto> getAllTableCategories(){
        List<TableCategory> tableCategories = tableCategoryRepository.findAll();
        List<TableCategoryDto> tableCategoryDtos = new ArrayList<>();
        tableCategories.forEach(tableCategory -> {
            TableCategoryDto tableCategoryDto = tableCategoryMapper.toDto(tableCategory);
            tableCategoryDtos.add(tableCategoryDto);
        });
        return tableCategoryDtos;
    }

    public TableCategoryDto getTableCategory(Long id){
        TableCategory tableCategory =  getTabCat(id);
        TableCategoryDto tableCategoryDto = tableCategoryMapper.toDto(tableCategory);
        return tableCategoryDto;
    }

    public TableCategory addTableCategory(TableCategoryDto tableCat){
        TableCategory tableCategory = tableCategoryMapper.toEntity(tableCat);
        return tableCategoryRepository.save(tableCategory);
    }

    public TableCategory editTableCategory(Long id, TableCategoryDto tabCat){
        TableCategory cat = getTabCat(id);
        cat.setName(tabCat.getName());
        cat.setNumber(tabCat.getNumber());
        return tableCategoryRepository.save(cat);
    }

    public void deleteTableCategory(Long id){
        tableCategoryRepository.deleteById(id);
    }


    private TableCategory getTabCat(Long id){
        Optional<TableCategory> opt =tableCategoryRepository.findById(id);
        return opt.orElse(null);
    }

}

