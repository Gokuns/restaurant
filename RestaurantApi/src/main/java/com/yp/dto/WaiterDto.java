package com.yp.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.Column;

@Data
@NoArgsConstructor
public class WaiterDto {
    private Long id;
    private Long waiterId;
    private String name;
    private String surname;
    @Column(name = "DATE_OF_BIRTH")
    private String dateOfBirth;
    private String phone;
    private String mail;
    private MediaDto media;
}
