package com.yp.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@EqualsAndHashCode(callSuper = true)
@SQLDelete(
        sql="UPDATE WAITERS SET DELETED= true WHERE ID=?")
@Where(clause = "DELETED=false")
@Entity(name = "WAITERS")
@Data
@NoArgsConstructor
public class Waiter extends BaseEntity{
    @NotNull(message = "Waiter waiterId cannot be null")
    @NotBlank(message = "Waiter waiterId cannot be blank")
    private Long waiterId;
    @NotNull(message = "Waiter name cannot be null")
    @NotBlank(message = "Waiter name cannot be blank")
    private String name;
    @NotNull(message = "Waiter surname cannot be null")
    @NotBlank(message = "Waiter surname cannot be blank")
    private String surname;
    @NotNull(message = "Waiter dateOfBirth cannot be null")
    @NotBlank(message = "Waiter dateOfBirth cannot be blank")
    private LocalDate dateOfBirth;
    @NotNull(message = "Waiter phone cannot be null")
    @NotBlank(message = "Waiter phone cannot be blank")
    private String phone;
    @NotNull(message = "Waiter mail cannot be null")
    @NotBlank(message = "Waiter mail cannot be blank")
    private String mail;

    @OneToOne
    @JoinColumn(name= "MEDIA_ID")
    private Media media;
}


