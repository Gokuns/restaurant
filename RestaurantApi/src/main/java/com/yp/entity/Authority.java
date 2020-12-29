package com.yp.entity;


import lombok.*;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

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
    @NotNull(message = "Role name cannot be null")
    @NotBlank(message = "Role Name cannot be blank")
    private String role;
}
