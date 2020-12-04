package com.yp.service;

import com.yp.dto.AuthorityDto;
import com.yp.dto.TableCategoryDto;
import com.yp.entity.Authority;
import com.yp.entity.TableCategory;
import com.yp.repos.AuthorityRepository;
import com.yp.repos.TableCategoryRepository;
import com.yp.service.AuthorityService;
import com.yp.service.TableCategoryService;
import junit.framework.TestCase;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class TableCategoryServiceTest extends TestCase {
    @InjectMocks
    private TableCategoryService tableCategoryService;

    @Mock
    private TableCategoryRepository tableCategoryRepository;

    private TableCategory tableCategory = new TableCategory();
    private TableCategory editTableCat = new TableCategory();
    private TableCategoryDto tableCategoryDto = new TableCategoryDto();
    private List<TableCategory> tableCategoryList = new ArrayList<>();
    private List<TableCategoryDto> tableCategoryDtoList = new ArrayList<>();


    @Before
    public void setUp(){
        tableCategory.setId(1);
        tableCategory.setNumber(5);
        tableCategory.setName("Outside");
        tableCategoryList.add(tableCategory);

        editTableCat.setId(1);
        editTableCat.setNumber(7);
        editTableCat.setName("Goko");

        tableCategoryDto.setId(1);
        tableCategoryDto.setId(5);
        tableCategoryDto.setName("Outside");
        tableCategoryDtoList.add(tableCategoryDto);




    }

    @Test
    public void shouldgetAuthorityWithId() {
        Mockito.when(tableCategoryRepository.findById(1)).thenReturn(Optional.of(tableCategory));
        TableCategoryDto auth = tableCategoryService.getTableCategory(1);
        assertNotNull(auth);
        assertEquals(tableCategory.getName(), auth.getName());
    }

    @Test
    public void getAuthorityList(){
        when(tableCategoryRepository.findAll()).thenReturn(tableCategoryList);
        List<TableCategoryDto> lst = tableCategoryService.getAllTableCategories();
        assertEquals(lst.get(0).getName(), tableCategoryDtoList.get(0).getName());
    }

    @Test
    public void shouldSaveWithDto() {
        when(tableCategoryRepository.save(any())).thenReturn(tableCategory);
        TableCategory tableCat = tableCategoryService.addTableCategory(tableCategoryDto);
        assertEquals(tableCategory.getName(), tableCat.getName());
    }

    @Test
    public void shouldEditWithId() {
        when(tableCategoryRepository.findById(any())).thenReturn(Optional.of(tableCategory));
        when(tableCategoryRepository.save(any())).thenReturn(tableCategory);
        TableCategory tableCat = tableCategoryService.editTableCategory(1,tableCategoryDto);
        assertNotNull(tableCat);

    }



}