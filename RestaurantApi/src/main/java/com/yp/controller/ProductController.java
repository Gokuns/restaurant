package com.yp.controller;

import com.yp.dto.ProductDto;
import com.yp.service.ProductService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Api(tags = "Product Controller")
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping
    @PreAuthorize("hasAuthority('ADMIN') || hasAuthority('USER')")
    public List<ProductDto> getAllProducts(){
        return  productService.getAllProducts();
    }

    @GetMapping("/sliced")
    @PreAuthorize("hasAuthority('ADMIN') || hasAuthority('USER')")
    public Slice<ProductDto> findAllSlice (Pageable pageable, @RequestParam(value = "lang", defaultValue = "en") String lang){
        return productService.getProductSlices(pageable, lang);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN') || hasAuthority('USER')")
    public ProductDto getProduct(@PathVariable(value = "id") Long id, @RequestParam(value = "lang", defaultValue = "en") String lang){
        return productService.getProduct(id, lang);
    }

    @PostMapping
    @PreAuthorize("hasAuthority('ADMIN')")
    public void addProduct(@Valid  @RequestBody ProductDto product, @RequestParam(value = "lang", defaultValue = "en") String lang){
        productService.addProduct(product, lang);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public void editProduct(@PathVariable(value = "id") Long id,@Valid @RequestBody ProductDto product, @RequestParam(value = "lang", defaultValue = "en") String lang){
        productService.editProduct(id, product, lang);
    }
    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public void deleteProduct(@PathVariable(value = "id") Long id, @RequestParam(value = "lang", defaultValue = "en") String lang){
        productService.deleteProduct(id, lang);
    }

    @GetMapping("/{id}/sliced")
    @PreAuthorize("hasAuthority('ADMIN') || hasAuthority('USER')")
    public Slice<ProductDto> getProductSliceWithCategory(@PathVariable(value = "id")Long id, Pageable pageable, @RequestParam(value = "lang", defaultValue = "en") String lang){
        return productService.getProductSliceWithCategory(id, pageable, lang);
    }

    @GetMapping("/paged")
    @PreAuthorize("hasAuthority('ADMIN') || hasAuthority('USER')")
    public Page<ProductDto> getProductPages(@RequestParam(defaultValue = "0") int page,
                                            @RequestParam(defaultValue = "20") int size,
                                            @RequestParam(value = "lang", defaultValue = "en") String lang){
        Pageable pageable = PageRequest.of(page,size);
        return productService.getProductPage(pageable, lang);
    }

}
