package com.yp.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@EqualsAndHashCode(callSuper = true)
@SQLDelete(
        sql="UPDATE CUSTOMERS SET DELETED= true WHERE ID=?")
@Where(clause = "DELETED=false")
@Entity(name = "CUSTOMERS")
@Data
@NoArgsConstructor
public class Customer extends BaseEntity{

    @Column(name = "NAME")
    @NotNull(message = "Customer name cannot be null")
    @NotBlank(message = "Customer name cannot be blank")
    private String name;
    @Column(name = "SURNAME")
    @NotNull(message = "Customer surname cannot be null")
    @NotBlank(message = "Customer surname cannot be blank")
    private String surname;
    @Column(name = "PHONE")
    @NotNull(message = "Customer phone cannot be null")
    @NotBlank(message = "Customer phone cannot be blank")
    private String phone;
    @Column(name = "ADDRESS")
    @NotNull(message = "Customer address cannot be null")
    @NotBlank(message = "Customer address cannot be blank")
    private String address;
    @ManyToOne
    @JoinColumn(name= "MEDIA_ID")
    @NotNull(message = "Customer media cannot be null")
    @NotBlank(message = "Customer media cannot be blank")
    private Media media;
}
