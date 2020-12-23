package com.yp.entity;


import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.awt.*;
import java.util.List;

@SQLDelete(
        sql="UPDATE Media SET deleted= true where id=?")
@Where(clause = "deleted=false")
@Entity
@Data
@NoArgsConstructor
public class Media {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    @Column(length = 100000)
    private byte[] fileContent;
}
