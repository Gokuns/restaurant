package com.yp.dto;

import com.yp.entity.Product;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@NoArgsConstructor
public class CategoryDto {
    private Long id;
    private String name;
    private MediaDto media;
}
