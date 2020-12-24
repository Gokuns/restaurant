package com.yp.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.util.Set;

@SQLDelete(
        sql="UPDATE CATEGORIES SET deleted= true where id=?")
@Where(clause = "deleted=false")
@Entity(name = "CATEGORIES")
@Data
@NoArgsConstructor
public class Category extends BaseEntity{

    private String name;


    @ManyToOne
    @JoinColumn(name= "media_id")
    private Media media;

  }
