package com.yp.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class MediaDto {

    private int id;
    private String name;
    private byte[] fileContent;
}
