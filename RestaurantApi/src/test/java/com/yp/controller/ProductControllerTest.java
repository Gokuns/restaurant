package com.yp.controller;

import com.yp.builder.ProductDtoBuilder;
import com.yp.dto.CategoryDto;
import com.yp.dto.ProductDto;
import com.yp.service.ProductService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.data.domain.PageRequest;

import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class ProductControllerTest {

    @InjectMocks
    private ProductController productController;

    @Mock
    private ProductService productService;

    private ProductDto productDto = new ProductDtoBuilder().build();
    private String lang = "en";

    @Before
    public void setUp() {

    }

    @Test
    public void shouldgetAuthorityWithId() {
        productController.getProduct(1L, lang);

        verify(productService,times(1)).getProduct(1L, lang);
    }

    @Test
    public void getAllList(){
        productController.getAllProducts();
        verify(productService,times(1)).getAllProducts();
    }

    @Test
    public void getAllSlice(){
        productController.findAllSlice(null, lang);
        verify(productService,times(1)).getProductSlices(null, lang);
    }

    @Test
    public void shouldSaveWithDto() {
        productController.addProduct(productDto, lang);
        verify(productService, times(1)).addProduct(productDto, lang);

    }

    @Test
    public void shouldEditWithDtop(){
        productController.editProduct(1L, productDto, lang);
        verify(productService, times(1)).editProduct(1L, productDto, lang);

    }

    @Test
    public void shouldDeleteWithId() {
        productController.deleteProduct(1L, lang);
        verify(productService, times(1)).deleteProduct(1L, lang);
    }

    @Test
    public void shouldGetSliceWithCategory(){
        productController.getProductSliceWithCategory(1L,null , lang);
        verify(productService, times(1)).getProductSliceWithCategory(1L,null , lang);
    }
    @Test
    public void shouldGetProductPage(){
        productController.getProductPages(0,1, lang);
        verify(productService, times(1)).getProductPage(PageRequest.of(0,1), lang);
    }
}