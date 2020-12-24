package com.yp.controller;

import com.yp.dto.ProductDto;
import com.yp.service.ProductService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(tags = "Product Controller")
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping("/list")
    public List<ProductDto> getAllProducts(){
        return  productService.getAllProducts();
    }

    @GetMapping("/list_2")
    public Slice<ProductDto> findAllSlice (Pageable pageable){
        return productService.getProductSlices(pageable);
    }

    @GetMapping("/{id}")
    public ProductDto getProduct(@PathVariable(value = "id") Long id){
        return productService.getProduct(id);
    }

    @PostMapping("/add")
    public void addProduct( @RequestBody ProductDto product){
        productService.addProduct(product);
    }

    @PutMapping("/{id}/put")
    public void editProduct(@PathVariable(value = "id") Long id, @RequestBody ProductDto product){
        productService.editProduct(id, product);
    }
    @DeleteMapping("/{id}/delete")
    public void deleteProduct(@PathVariable(value = "id") Long id){
        productService.deleteProduct(id);
    }

    @GetMapping("/list_2/{id}")
    public Slice<ProductDto> getProductSliceWithCategory(@PathVariable(value = "id")Long id, Pageable pageable){
        return productService.getProductSliceWithCategory(id, pageable);
    }

    @GetMapping("/list_3")
    public Page<ProductDto> getProductPages(@RequestParam(defaultValue = "0") int page,
                                            @RequestParam(defaultValue = "20") int size){
        Pageable pageable = PageRequest.of(page,size);
        return productService.getProductPage(pageable);
    }

}
