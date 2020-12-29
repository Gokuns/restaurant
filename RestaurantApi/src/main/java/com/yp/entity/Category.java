package com.yp.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;
import javax.persistence.*;

@EqualsAndHashCode(callSuper = true)
@SQLDelete(
        sql="UPDATE CATEGORIES SET DELETED= true WHERE ID=?")
@Where(clause = "DELETED=false")
@Entity(name = "CATEGORIES")
@Data
@NoArgsConstructor
public class Category extends BaseEntity{

    @Column(name = "NAME")
    private String name;


    @ManyToOne
    @JoinColumn(name= "MEDIA_ID")
    private Media media;

  }
