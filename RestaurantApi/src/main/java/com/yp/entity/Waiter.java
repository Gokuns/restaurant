package com.yp.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.time.LocalDate;

@EqualsAndHashCode(callSuper = true)
@SQLDelete(
        sql="UPDATE Waiter SET deleted= true where id=?")
@Where(clause = "deleted=false")
@Entity
@Data
@NoArgsConstructor
public class Waiter extends BaseEntity{
    private Long waiterId;
    private String name;
    private String surname;
    private LocalDate dateOfBirth;
    private String phone;
    private String mail;

    @OneToOne
    @JoinColumn(name= "media_id")
    private Media media;
}


