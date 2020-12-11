package com.yp.service;

import com.yp.builder.CategoryBuilder;
import com.yp.builder.CategoryDtoBuilder;
import com.yp.builder.ProductBuilder;
import com.yp.builder.ProductDtoBuilder;
import com.yp.dto.CategoryDto;
import com.yp.dto.ProductDto;
import com.yp.entity.Category;
import com.yp.entity.Product;
import com.yp.repos.CategoryRepository;
import com.yp.repos.ProductRepository;
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
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class ProductServiceTest extends TestCase {

    @InjectMocks
    private ProductService productService;

    @Mock
    private ProductRepository productRepository;

    @Mock
    private CategoryRepository categoryRepository;

    private Product product = new ProductBuilder().build();
    private ProductDto productDto = new ProductDtoBuilder().build();
    private List<Product> products = new ArrayList<>();
    private List<ProductDto> productDtos = new ArrayList<>();
    private Category cat = new CategoryBuilder().build();
    private CategoryDto catDto = new CategoryDtoBuilder().build();


    @Before
    public void setUp() {
        products.add(product);
        productDtos.add(productDto);


    }

    @Test
    public void shouldgetAuthorityWithId() {
        when(productRepository.findById(1)).thenReturn(Optional.of(product));
        ProductDto p = productService.getProduct(1);
        assertNotNull(p);
        assertEquals(p.getName(), product.getName());
    }

    @Test
    public void getAuthorityList(){
        when(productRepository.findAll()).thenReturn(products);
        List<ProductDto> lst = productService.getAllProducts();
        assertEquals(lst.get(0).getName(), products.get(0).getName());

    }

    @Test
    public void shouldSaveWithDto() {
        when(productRepository.save(any())).thenReturn(product);
        when(categoryRepository.findById(any())).thenReturn(Optional.of(cat));
        Product p = productService.addProduct(productDto);
        assertEquals(p.getName(), product.getName());
    }

    @Test
    public void shouldEditWithId() {
        when(productRepository.findById(any())).thenReturn(Optional.of(product));
        when(productRepository.save(any())).thenReturn(product);
        Product p = productService.editProduct(1, productDto);
        assertNotNull(p);
        assertEquals(p.getName(), product.getName());
    }

    @Test
    public void shouldDeleteWithId() {
        productService.deleteProduct(1);
        verify(productRepository, times(1)).deleteById(any());

    }
}