package com.yp.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@NoArgsConstructor
public class ProductDto {
    private Long id;
    private String name;
    private String details;
    private MediaDto media;
    private double price;
    private Set<CategoryDto> categories;
}
