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
        sql="UPDATE TABLE_CATEGORIES SET DELETED= true WHERE ID=?")
@Where(clause = "DELETED=false")
@Entity(name = "TABLE_CATEGORIES")
@Data
@NoArgsConstructor
public class    TableCategory extends BaseEntity{
    @NotNull(message = "TableCategory name cannot be null")
    @NotBlank(message = "TableCategory name cannot be blank")
    private String name;
    @NotNull(message = "TableCategory number cannot be null")
    @NotBlank(message = "TableCategory number cannot be blank")
    private int number;
    @ManyToOne
    @JoinColumn(name= "MEDIA_ID")
    private Media media;
}
