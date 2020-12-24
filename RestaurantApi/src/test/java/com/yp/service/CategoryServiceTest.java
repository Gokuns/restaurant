package com.yp.service;

import com.yp.builder.CategoryBuilder;
import com.yp.builder.CategoryDtoBuilder;
import com.yp.builder.MediaBuilder;
import com.yp.builder.ProductBuilder;
import com.yp.dto.CategoryDto;
import com.yp.dto.ProductDto;
import com.yp.entity.Category;
import com.yp.entity.Media;
import com.yp.entity.Product;
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
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

    private Category category = new CategoryBuilder().build();
    private CategoryDto categoryDto = new CategoryDtoBuilder().build();
    private List<Category> categoryList = new ArrayList<>();
    private List<CategoryDto> categoryDtoList = new ArrayList<>();
    private List<Product> products = new ArrayList<>();
    private Product product = new ProductBuilder().build();
    private Media media = new MediaBuilder().build();

    @Before
    public void setUp() {
        products.add(product);
        categoryList.add(category);
        categoryDtoList.add(categoryDto);
        when(categoryMapper.toDto(any())).thenReturn(categoryDto);
        when(categoryMapper.toEntity(any())).thenReturn(category);
        when(mediaMapper.toEntity(any())).thenReturn(media);
    }

    @Test
    public void shouldgetAuthorityWithId() {
        when(categoryRepository.findById(1L)).thenReturn(Optional.of(category));
        CategoryDto cat = categoryService.getCategory(1L);
        assertEquals(cat.getName(), category.getName());
    }

    @Test
    public void getAuthorityList(){
        when(categoryRepository.findAll()).thenReturn(categoryList);
        List<CategoryDto> lst = categoryService.getAllCategory();
        assertEquals(lst.get(0).getName(), categoryList.get(0).getName());
    }

    @Test
    public void shouldSaveWithDto() {
        when(categoryRepository.save(any())).thenReturn(category);
        Category cat = categoryService.addCategory(categoryDto);
        assertEquals(cat.getId(), categoryDto.getId());
    }

    @Test
    public void shouldEditWithId() {
        when(categoryRepository.save(any())).thenReturn(category);
        when(categoryRepository.findById(any())).thenReturn(Optional.of(category));
        Category cat = categoryService.editCategory(1L, categoryDto);
        assertEquals(cat.getId(), categoryDto.getId());

    }

    @Test
    public void shouldDeleteWithId() {
        categoryService.deleteCategory(1L);
        verify(categoryRepository, times(1)).deleteById(any());

    }

    @Test
    public void shouldGetProductsFromCat(){
        when(categoryRepository.findById(any())).thenReturn(Optional.of(category));
        when(productRepository.findAllByCategoriesIn(any())).thenReturn(products);
        List<ProductDto> lst = categoryService.getProductsWithId(1L);
        assertNotNull(lst);
    }
}