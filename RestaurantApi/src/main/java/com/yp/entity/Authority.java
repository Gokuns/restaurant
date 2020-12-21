package com.yp.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Getter;
import javax.persistence.*;


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
