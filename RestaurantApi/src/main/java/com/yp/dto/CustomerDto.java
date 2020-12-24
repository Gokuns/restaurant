package com.yp.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CustomerDto {
    private Long id;
    private String name;
    private String surname;
    private String phone;
    private String address;
}
