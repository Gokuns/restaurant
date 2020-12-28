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
    public void getAuthorityList(){
        productController.getAllProducts();
        verify(productService,times(1)).getAllProducts();
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
}