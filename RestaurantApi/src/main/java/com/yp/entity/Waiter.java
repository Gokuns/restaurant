package com.yp.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
public class Waiter {
    @Id
    private Long id;
    private String name;
    private String surname;
    private LocalDate dateOfBirth;
    private String phone;
    private String mail;

    @OneToOne
    @JoinColumn(name= "media_id")
    private Media media;
}


