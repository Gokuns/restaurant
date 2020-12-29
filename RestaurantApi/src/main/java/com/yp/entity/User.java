package com.yp.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Set;

@EqualsAndHashCode(callSuper = true)
@SQLDelete(
        sql="UPDATE USERS SET DELETED= true WHERE ID=?")
@Where(clause = "DELETED=false")
@Entity(name = "USERS")
@Data
@NoArgsConstructor
public class User extends BaseEntity {

    @Column(name = "USERNAME" , unique = true)
    @NotNull(message = "Username cannot be null")
    @NotBlank(message = "Username cannot be blank")
    private String username;
    @Column(name = "PASSWORD")
    @NotNull(message = "User password cannot be null")
    @NotBlank(message = "User password cannot be blank")
    @Min(value = 4, message = "Must be minimum 4 characters")
    private String password;
    @Column(name = "ENABLED")
    @NotNull(message = "User enabled cannot be null")
    private boolean enabled;

    @ManyToMany(cascade = {CascadeType.MERGE}, fetch = FetchType.EAGER)
    @JoinTable(
            name = "AUTHORITIES",
            joinColumns = {@JoinColumn(name = "USER_ID")},
            inverseJoinColumns = {@JoinColumn(name = "AUTHORITY_ID")}

    )
    private Set<Authority> authorities;
}
