package com.yp.service;

import com.yp.builder.MediaBuilder;
import com.yp.builder.TableCategoryBuilder;
import com.yp.builder.TableCategoryDtoBuilder;
import com.yp.dto.TableCategoryDto;
import com.yp.entity.Media;
import com.yp.entity.TableCategory;
import com.yp.exception.BusinessRuleException;
import com.yp.exception.ContentNotFoundException;
import com.yp.mapper.MediaMapper;
import com.yp.mapper.TableCategoryMapper;
import com.yp.repos.TableCategoryRepository;
import junit.framework.TestCase;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.context.MessageSource;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class TableCategoryServiceTest extends TestCase {
    @InjectMocks
    private TableCategoryService tableCategoryService;

    @Mock
    private TableCategoryRepository tableCategoryRepository;

    @Mock
    private TableCategoryMapper tableCategoryMapper;
    @Mock
    private MessageSource messageSource;
    @Mock
    private MediaMapper mediaMapper;


    private TableCategory tableCategory = new TableCategoryBuilder().build();
    private TableCategory editTableCat = new TableCategoryBuilder().build();
    private TableCategoryDto tableCategoryDto = new TableCategoryDtoBuilder().build();
    private List<TableCategory> tableCategoryList = new ArrayList<>();
    private List<TableCategoryDto> tableCategoryDtoList = new ArrayList<>();
    private String lang = "en";


    @Before
    public void setUp(){
        tableCategoryList.add(tableCategory);
        tableCategoryDtoList.add(tableCategoryDto);
        when(tableCategoryMapper.toDto(any())).thenReturn(tableCategoryDto);
        when(tableCategoryMapper.toEntity(any())).thenReturn(tableCategory);
    }

    @Test(expected = BusinessRuleException.class)
    public void shouldRaiseExceptionInGetWithId(){
        tableCategoryService.getTableCategory(null, lang);
    }

    @Test(expected = ContentNotFoundException.class)
    public void shouldRaiseExceptionInGetWithNotFound(){
        tableCategoryService.getTableCategory(2L, lang);
    }

    @Test
    public void shouldgetAuthorityWithId() {
        Mockito.when(tableCategoryRepository.findById(1L)).thenReturn(Optional.of(tableCategory));
        TableCategoryDto auth = tableCategoryService.getTableCategory(1L, lang);
        assertNotNull(auth);
        assertEquals(tableCategory.getName(), auth.getName());
    }

    @Test
    public void getAuthorityList(){
        when(tableCategoryRepository.findAll()).thenReturn(tableCategoryList);
        List<TableCategoryDto> lst = tableCategoryService.getAllTableCategories();
        assertEquals(lst.get(0).getName(), tableCategoryDtoList.get(0).getName());
    }

    @Test(expected = BusinessRuleException.class)
    public void shouldRaiseExceptionInAddWithObject(){
        tableCategoryService.addTableCategory(null, lang);
    }

    @Test
    public void shouldSaveWithDto() {
        when(tableCategoryRepository.save(any())).thenReturn(tableCategory);
        TableCategory tableCat = tableCategoryService.addTableCategory(tableCategoryDto, lang);
        assertEquals(tableCategory.getName(), tableCat.getName());
    }

    @Test(expected = BusinessRuleException.class)
    public void shouldRaiseExceptionInEditWithId(){
        tableCategoryService.editTableCategory(null, tableCategoryDto,lang);
    }
    @Test(expected = BusinessRuleException.class)
    public void shouldRaiseExceptionInEditWithObject(){
        tableCategoryService.editTableCategory(1L, null, lang);
    }

    @Test
    public void shouldEditWithId() {
        Media media = new MediaBuilder().withId(2L).build();
        TableCategory tableCategory = new TableCategoryBuilder().withId(2L).withName("test")
                .withNumber(3).withMedia(media).build();
        when(tableCategoryRepository.findById(any())).thenReturn(Optional.of(tableCategory));
        when(tableCategoryRepository.save(any())).thenReturn(tableCategory);
        TableCategory tableCat = tableCategoryService.editTableCategory(1L,tableCategoryDto, lang);
        assertNotNull(tableCat);

    }

    @Test(expected = BusinessRuleException.class)
    public void shouldRaiseExceptionInDeleteWithId(){
        tableCategoryService.deleteTableCategory(null, lang);
    }

    @Test
    public void shouldDeleteWithId() {
        tableCategoryService.deleteTableCategory(1L, lang);
        verify(tableCategoryRepository, times(1)).deleteById(any());

    }



}