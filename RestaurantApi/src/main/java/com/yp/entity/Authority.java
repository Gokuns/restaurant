package com.yp.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Getter;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;

@SQLDelete(
        sql="UPDATE ROLES SET deleted= true where id=?")
@Where(clause = "deleted=false")
@Entity(name = "ROLES")
@Data
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Authority {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int id;
    @Column(name= "AUTHORITY", unique = true)
    private String authority;
}
