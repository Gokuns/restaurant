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
        sql="UPDATE CATEGORIES SET DELETED= true WHERE ID=?")
@Where(clause = "DELETED=false")
@Entity(name = "CATEGORIES")
@Data
@NoArgsConstructor
public class Category extends BaseEntity{

    @Column(name = "NAME")
    @NotNull(message = "Category name cannot be null")
    @NotBlank(message = "Category Name cannot be blank")
    private String name;


    @ManyToOne
    @JoinColumn(name= "MEDIA_ID")
    @NotNull(message = "Category media cannot be null")
    private Media media;

  }
