package com.yp.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;
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
    private String username;
    @Column(name = "PASSWORD")
    private String password;
    @Column(name = "ENABLED")
    private boolean enabled;

    @ManyToMany(cascade = {CascadeType.MERGE}, fetch = FetchType.EAGER)
    @JoinTable(
            name = "AUTHORITIES",
            joinColumns = {@JoinColumn(name = "USER_ID")},
            inverseJoinColumns = {@JoinColumn(name = "AUTHORITY_ID")}

    )
    private Set<Authority> authorities;
}
