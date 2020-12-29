package com.yp.service;

import com.yp.builder.*;
import com.yp.dto.CategoryDto;
import com.yp.dto.MediaDto;
import com.yp.dto.ProductDto;
import com.yp.dto.UserDto;
import com.yp.entity.Authority;
import com.yp.entity.Category;
import com.yp.entity.Media;
import com.yp.entity.Product;
import com.yp.exception.BusinessRuleException;
import com.yp.exception.ContentNotFoundException;
import com.yp.mapper.ProductMapper;
import com.yp.repos.AuthorityRepository;
import com.yp.repos.CategoryRepository;
import com.yp.repos.ProductRepository;
import junit.framework.TestCase;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.context.MessageSource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Slice;

import java.util.*;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class ProductServiceTest extends TestCase {

    @InjectMocks
    private ProductService productService;

    @Mock
    private ProductRepository productRepository;

    @Mock
    private CategoryRepository categoryRepository;
    @Mock
    private ProductMapper productMapper;
    @Mock
    private MessageSource messageSource;

    private final Product product = new ProductBuilder().build();
    private final ProductDto productDto = new ProductDtoBuilder().build();
    private final List<Product> products = new ArrayList<>();
    private final List<ProductDto> productDtos = new ArrayList<>();
    private final Category cat = new CategoryBuilder().build();
    private final CategoryDto catDto = new CategoryDtoBuilder().build();
    private final String lang = "en";
    private final Slice<Product> productSlice = new SliceBuilder<Product>().build();
    private final Page<Product> productPage = new PageBuilder<Product>().build();


    @Before
    public void setUp() {
        products.add(product);
        productDtos.add(productDto);
        when(productMapper.toDto(product)).thenReturn(productDto);
        when(productMapper.toEntity(productDto)).thenReturn(product);
        when(productRepository.findById(1L)).thenReturn(Optional.of(product));
        when(productRepository.save(product)).thenReturn(product);
        when(productRepository.findAllByCategories(any(), any())).thenReturn(productSlice);
        when(productRepository.findAllPages(any())).thenReturn(productPage);
        when(productRepository.findAllSlice(any())).thenReturn(productSlice);
        when(categoryRepository.findById(1L)).thenReturn(Optional.of(cat));
    }


    @Test(expected = BusinessRuleException.class)
    public void shouldRaiseExceptionInGetWithId(){
        productService.getProduct(null, lang);
    }

    @Test(expected = ContentNotFoundException.class)
    public void shouldRaiseExceptionInGetWithObject(){
        productService.getProduct(2L, lang);
    }


    @Test
    public void shouldgetProductWithId() {
        ProductDto p = productService.getProduct(1L, lang);
        assertNotNull(p);
        assertEquals(p.getName(), product.getName());
    }


    @Test
    public void getAuthorityList(){
        when(productRepository.findAll()).thenReturn(products);
        List<ProductDto> lst = productService.getAllProducts();
        assertEquals(lst.get(0).getName(), products.get(0).getName());
    }


    @Test(expected = BusinessRuleException.class)
    public void shouldRaiseExceptionInAddWithObject(){
        productService.addProduct(null, lang);
    }

    @Test
    public void shouldSaveWithDto() {
        Product p = productService.addProduct(productDto, lang);
        assertEquals(p.getName(), product.getName());
    }

    @Test(expected = BusinessRuleException.class)
    public void shouldRaiseExceptionInEditWithId(){
        productService.editProduct(null, productDto, lang);
    }
    @Test(expected = BusinessRuleException.class)
    public void shouldRaiseExceptionInEditWithObject(){
        productService.editProduct(1L, null, lang);

    }
    @Test(expected = ContentNotFoundException.class)
    public void shouldRaiseExceptionInEditWithNotFound(){
        productService.editProduct(2L, productDto, lang);
    }

    @Test
    public void shouldEditWithId() {
        Media media = new MediaBuilder().withId(3L).build();
        Set<Category> categories = new HashSet<>();
        Product prod = new ProductBuilder().withId(2L).withName("test").withDetails("test").withPrice(987.123)
                .withMedia(media).withCategory(categories).build();
        when(productMapper.toEntity(productDto)).thenReturn(prod);
        Product p = productService.editProduct(1L, productDto, lang);
        assertNotNull(p);
    }

    @Test(expected = BusinessRuleException.class)
    public void shouldRaiseExceptionInSliceWithPageable(){
        productService.getProductSlices(null, lang);
    }
    @Test
    public void shouldGetSlice() {
        Slice<ProductDto> slices = productService.getProductSlices(PageRequest.of(0, 1), lang);
        assertNotNull(slices);
    }

    @Test(expected = BusinessRuleException.class)
    public void shouldRaiseExceptionInSliceCatWithId(){
        productService.getProductSliceWithCategory(null,PageRequest.of(0,1), lang);
    }
    @Test(expected = ContentNotFoundException.class)
    public void shouldRaiseExceptionInSliceCatWithObject(){
        productService.getProductSliceWithCategory(2L,PageRequest.of(0,1), lang);
    }

    @Test
    public void shouldGetSliceCat() {
        Slice<ProductDto> slice = productService.getProductSliceWithCategory(1L, PageRequest.of(0, 1), lang);
        assertNotNull(slice);
    }

    @Test(expected = BusinessRuleException.class)
    public void shouldRaiseExceptionInPageWithPageable(){
        productService.getProductPage(null, lang);
    }

    @Test
    public void shouldGetPage(){
        Page<ProductDto> productPage = productService.getProductPage(PageRequest.of(0, 1), lang);
        assertNotNull(productPage);

    }

    @Test(expected = BusinessRuleException.class)
    public void shouldRaiseExceptionInDeleteWithId(){
        productService.deleteProduct(null, lang);
    }

    @Test
    public void shouldDeleteWithId() {
        productService.deleteProduct(1L, lang);
        verify(productRepository, times(1)).deleteById(any());
    }


}