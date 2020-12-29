package com.yp.entity;


import lombok.*;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;

@EqualsAndHashCode(callSuper = true)
@SQLDelete(
        sql="UPDATE ROLES SET DELETED= true WHERE ID=?")
@Where(clause = "DELETED=false")
@Entity(name = "ROLES")
@Data
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Authority extends BaseEntity{

    @Column(name= "AUTHORITY", unique = true)
    private String role;
}
