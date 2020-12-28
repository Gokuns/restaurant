package com.yp.service;

import com.yp.dto.TableCategoryDto;
import com.yp.entity.TableCategory;
import com.yp.exception.BusinessRuleException;
import com.yp.exception.ContentNotFoundException;
import com.yp.mapper.TableCategoryMapper;
import com.yp.repos.TableCategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TableCategoryService {
    @Autowired
    private TableCategoryRepository tableCategoryRepository;
    @Autowired
    private TableCategoryMapper tableCategoryMapper;
    @Autowired
    private MessageSource messageSource;
    private static final String BUSINESS_RULE_EXCEPTION = "BusinessRuleException";
    private static final String CONTENT_NOT_FOUND = "ContentNotFound";

    public List<TableCategoryDto> getAllTableCategories() {
        return tableCategoryRepository.findAll().stream().map(tableCategoryMapper::toDto).collect(Collectors.toList());
    }

    public TableCategoryDto getTableCategory(Long id, String lang){
        if(id==null){
            throw new BusinessRuleException(messageSource.getMessage(BUSINESS_RULE_EXCEPTION, new Object[0], new Locale(lang)));
        }
        TableCategory tableCategory =  getTabCat(id, lang);
        return tableCategoryMapper.toDto(tableCategory);
    }

    public TableCategory addTableCategory(TableCategoryDto tableCat, String lang){
        if(tableCat==null){
            throw new BusinessRuleException(messageSource.getMessage(BUSINESS_RULE_EXCEPTION, new Object[0], new Locale(lang)));
        }
        TableCategory tableCategory = tableCategoryMapper.toEntity(tableCat);
        return tableCategoryRepository.save(tableCategory);
    }

    public TableCategory editTableCategory(Long id, TableCategoryDto tabCat, String lang){
        if(id==null){
            throw new BusinessRuleException(messageSource.getMessage(BUSINESS_RULE_EXCEPTION, new Object[0], new Locale(lang)));
        }
        if(tabCat==null){
            throw new BusinessRuleException(messageSource.getMessage(BUSINESS_RULE_EXCEPTION, new Object[0], new Locale(lang)));
        }
        TableCategory cat = getTabCat(id, lang);
        if(!cat.getName().equals(tabCat.getName())){
            cat.setName(tabCat.getName());
        }
        if(cat.getNumber()!=tabCat.getNumber()){
            cat.setNumber(tabCat.getNumber());
        }
        return tableCategoryRepository.save(cat);
    }

    public void deleteTableCategory(Long id, String lang){
        if(id==null){
            throw new BusinessRuleException(messageSource.getMessage(BUSINESS_RULE_EXCEPTION, new Object[0], new Locale(lang)));
        }
        tableCategoryRepository.deleteById(id);
    }

    private TableCategory getTabCat(Long id, String lang){
        if(id==null){
            throw new BusinessRuleException(messageSource.getMessage(BUSINESS_RULE_EXCEPTION, new Object[0], new Locale(lang)));
        }
        Optional<TableCategory> opt =tableCategoryRepository.findById(id);
        if (opt.isEmpty()){
            throw new ContentNotFoundException(messageSource.getMessage(CONTENT_NOT_FOUND, new Object[0], new Locale(lang)));
        }
        return opt.get();
    }

}

