package com.yp.service;

import com.yp.builder.*;
import com.yp.dto.CategoryDto;
import com.yp.dto.MediaDto;
import com.yp.dto.ProductDto;
import com.yp.entity.Category;
import com.yp.entity.Media;
import com.yp.entity.Product;
import com.yp.exception.BusinessRuleException;
import com.yp.exception.ContentNotFoundException;
import com.yp.mapper.CategoryMapper;
import com.yp.mapper.MediaMapper;
import com.yp.mapper.ProductMapper;
import com.yp.repos.CategoryRepository;
import com.yp.repos.ProductRepository;
import junit.framework.TestCase;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.context.MessageSource;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.Sort;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class CategoryServiceTest extends TestCase {

    @InjectMocks
    private CategoryService categoryService;

    @Mock
    private CategoryRepository categoryRepository;

    @Mock
    private ProductRepository productRepository;

    @Mock
    private CategoryMapper categoryMapper;

    @Mock
    private ProductMapper productMapper;

    @Mock
    private MediaMapper mediaMapper;
    @Mock
    private MessageSource messageSource;

    private final Category category = new CategoryBuilder().build();
    private final CategoryDto categoryDto = new CategoryDtoBuilder().build();
    private final List<Category> categoryList = new ArrayList<>();
    private final List<CategoryDto> categoryDtoList = new ArrayList<>();
    private final List<Product> products = new ArrayList<>();
    private final Product product = new ProductBuilder().build();
    private final String lang = "en";

    @Before
    public void setUp() {
        products.add(product);
        categoryList.add(category);
        categoryDtoList.add(categoryDto);
        when(categoryMapper.toDto(any())).thenReturn(categoryDto);
        when(categoryMapper.toEntity(categoryDto)).thenReturn(category);
        when(categoryRepository.findById(1L)).thenReturn(Optional.of(category));
        when(productRepository.findAllByCategoriesIn(categoryList)).thenReturn(products);
        when(categoryRepository.save(any())).thenReturn(category);

    }

    @Test
    public void shouldgetCategoryWithId() {
        CategoryDto cat = categoryService.getCategory(1L, lang);
        assertEquals(cat.getName(), category.getName());
    }

    @Test
    public void getCategoryList(){
        List<CategoryDto> lst = categoryService.getAllCategory();
        assertNotNull(lst);
    }

    @Test
    public void shouldSaveWithDto() {
        Category cat = categoryService.addCategory(categoryDto, lang);
        assertEquals(cat.getId(), categoryDto.getId());
    }

    @Test
    public void shouldEditWithId() {
        CategoryDto dto = new CategoryDtoBuilder().withName("test").withMedia(new MediaDtoBuilder().withName("test").build()).build();
        Category cat = categoryService.editCategory(1L, dto, lang);
        assertEquals(cat.getId(), categoryDto.getId());
    }

    @Test
    public void shouldDeleteWithId() {
        categoryService.deleteCategory(1L, lang);
        verify(categoryRepository, times(1)).deleteById(any());

    }

    @Test
    public void shouldGetProductsFromCat(){
        List<ProductDto> lst = categoryService.getProductsWithCategoryId(1L, lang);
        assertNotNull(lst);
    }
    @Test(expected = BusinessRuleException.class)
    public void shouldRaiseExceptionInFromCatWithId(){
        List<ProductDto> lst = categoryService.getProductsWithCategoryId(null, lang);
    }
    @Test(expected = ContentNotFoundException.class)
    public void shouldRaiseExceptionInFromCatWithNotFound(){
        List<ProductDto> lst = categoryService.getProductsWithCategoryId(2L, lang);
    }
    @Test
    public void shouldgetProductsOfCat(){
        List<ProductDto> lst = categoryService.getProductsWithCategoryId(1L, lang);
        assertNotNull(lst);
    }
    @Test(expected = BusinessRuleException.class)
    public void shouldRaiseExceptionInGetWithCat(){
        categoryService.getProductsWithCategoryId(null, lang);
    }

    @Test(expected = BusinessRuleException.class)
    public void shouldRaiseExceptionInEditWithId(){
        CategoryDto dto = new CategoryDtoBuilder().withName("test").withMedia(new MediaDtoBuilder().withName("test").build()).build();
        Category cat = categoryService.editCategory(null, dto, lang);
    }
    @Test(expected = BusinessRuleException.class)
    public void shouldRaiseExceptionInEditWithObject(){
        Category cat = categoryService.editCategory(1L, null, lang);
    }

    @Test(expected = ContentNotFoundException.class)
    public void shouldRaiseExceptionInEditWithNotFound(){
        CategoryDto dto = new CategoryDtoBuilder().withName("test").withMedia(new MediaDtoBuilder().withName("test").build()).build();
        Category cat = categoryService.editCategory(2L, dto, lang);
    }
    @Test(expected = BusinessRuleException.class)
    public void shouldRaiseExceptionInDeleteWithId(){
       categoryService.deleteCategory(null, lang);
    }
    @Test(expected = BusinessRuleException.class)
    public void shouldRaiseExceptionGetCategoryWithId(){
        categoryService.getCategory(null, lang);
    }
    @Test(expected = ContentNotFoundException.class)
    public void shouldRaiseExceptionGetCategoryWithNotFound(){
        categoryService.getCategory(2L,lang);
    }

    @Test(expected = ContentNotFoundException.class)
    public void shouldRaiseExceptionGetSliceCategoryWithNotFound(){
        categoryService.getProductsSliceWithCategoryId(2L, PageRequest.of(0,1),lang);
    }



    @Test
    public void shouldgetProductSliceWithCatId(){
        when(productRepository.findAllByCategories(any(), any())).thenReturn(new SliceBuilder<Product>().build());
        categoryService.getProductsSliceWithCategoryId(1L, PageRequest.of(0,10), lang);
        verify(productRepository, times(1)).findAllByCategories(any(), any());
    }



}