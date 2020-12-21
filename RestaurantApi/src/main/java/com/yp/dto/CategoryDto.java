package com.yp.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CategoryDto {
    private int id;
    private String name;
    private MediaDto media;
}
