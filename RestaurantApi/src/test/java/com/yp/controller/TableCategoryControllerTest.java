package com.yp.controller;

import com.yp.dto.TableCategoryDto;
import com.yp.service.TableCategoryService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class TableCategoryControllerTest {

    @InjectMocks
    private TableCategoryController tableCategoryController;

    @Mock
    private TableCategoryService tableCategoryService;

    private TableCategoryDto tableCategoryDto = new TableCategoryDto();
    private String lang = "en";

    @Before
    public void setUp(){
    }

    @Test
    public void shouldgetWithId() {
        tableCategoryController.getTableCat(1L, lang);
        verify(tableCategoryService,times(1)).getTableCategory(1L, lang);
    }


    @Test
    public void shouldgetList(){

        tableCategoryController.getCats();
        verify(tableCategoryService,times(1)).getAllTableCategories();
    }

    @Test
    public void shouldEditWithDto(){
        tableCategoryController.editTableCat(1L, tableCategoryDto, lang);
        verify(tableCategoryService, times(1)).editTableCategory(1L, tableCategoryDto, lang);
    }

    @Test
    public void shouldSaveWithDto() {
        tableCategoryController.addTableCat(tableCategoryDto, lang);
        verify(tableCategoryService, times(1)).addTableCategory(tableCategoryDto, lang);

    }

    @Test
    public void shouldDeleteWithId() {
        tableCategoryController.deleteTableCat(1L, lang);
        verify(tableCategoryService, times(1)).deleteTableCategory(1L, lang);
    }

}