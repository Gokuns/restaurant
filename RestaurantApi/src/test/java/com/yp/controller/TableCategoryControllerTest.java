package com.yp.controller;

import com.yp.dto.CategoryDto;
import com.yp.dto.TableCategoryDto;
import com.yp.entity.TableCategory;
import com.yp.service.ProductService;
import com.yp.service.TableCategoryService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import static org.junit.Assert.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class TableCategoryControllerTest {

    @InjectMocks
    private TableCategoryController tableCategoryController;

    @Mock
    private TableCategoryService tableCategoryService;

    private TableCategoryDto tableCategoryDto = new TableCategoryDto();

    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void shouldgetWithId() {
        tableCategoryController.getTableCat(1);
        verify(tableCategoryService,times(1)).getTableCategory(1);
    }


    @Test
    public void shouldgetList(){

        tableCategoryController.getCats();
        verify(tableCategoryService,times(1)).getAllTableCategories();
    }

    @Test
    public void shouldgetNameWithId() {
        Mockito.when(tableCategoryService.getTableCategory(1)).thenReturn(tableCategoryDto);

        tableCategoryController.getCatName(1);
        verify(tableCategoryService,times(1)).getTableCategory(1);
    }

    @Test
    public void shouldEditWithDto(){
        tableCategoryController.editTableCat(1, tableCategoryDto);
        verify(tableCategoryService, times(1)).editTableCategory(1, tableCategoryDto);
    }

    @Test
    public void shouldSaveWithDto() {
        tableCategoryController.addTableCat(tableCategoryDto);
        verify(tableCategoryService, times(1)).addTableCategory(tableCategoryDto);

    }

    @Test
    public void shouldDeleteWithId() {
        tableCategoryController.deleteTableCat(1);
        verify(tableCategoryService, times(1)).deleteTableCategory(1);
    }

}