package com.yp.controller;

import com.yp.dto.CategoryDto;
import com.yp.service.CategoryService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;


import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class CategoryControllerTest {

    @InjectMocks
    private CategoryController categoryController;

    @Mock
    private CategoryService categoryService;


    private CategoryDto categoryDto = new CategoryDto();
    private String lang= "en";

    @Before
    public void setUp(){
        categoryDto.setId(1L);
        categoryDto.setName("Goko");

    }

    @Test
    public void shouldgetWithId() {

        categoryController.getCat(1L,lang);
        verify(categoryService,times(1)).getCategory(1L,lang);
    }

    @Test
    public void shouldgetList(){
        categoryController.getAllCats();
        verify(categoryService,times(1)).getAllCategory();
    }

    @Test
    public void shouldEditWithDto(){
        categoryController.editCategory(1L,  categoryDto, lang);
        verify(categoryService, times(1)).editCategory(1L, categoryDto,lang);
    }

    @Test
    public void shouldSaveWithDto() {
        categoryController.addCategory(categoryDto,lang);
        verify(categoryService, times(1)).addCategory(categoryDto,lang);

    }

    @Test
    public void shouldDeleteWithId() {
        categoryController.deleteCategory(1L, lang);
        verify(categoryService, times(1)).deleteCategory(1L, lang);
    }

    @Test
    public void shouldgetProdsWithId() {
        categoryController.getProductsofCategory(1L, lang);
        verify(categoryService,times(1)).getProductsWithCategoryId(1L, lang);
    }

}