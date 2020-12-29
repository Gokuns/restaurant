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
        sql="UPDATE WAITERS SET DELETED= true WHERE ID=?")
@Where(clause = "DELETED=false")
@Entity(name = "WAITERS")
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
    @JoinColumn(name= "MEDIA_ID")
    private Media media;
}


