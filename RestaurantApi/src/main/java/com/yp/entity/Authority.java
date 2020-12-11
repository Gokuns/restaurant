package com.yp.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity(name = "ROLES")
public class Authority {
    @Id
    @Column(name= "AUTHORITY", unique = true)
    private String authority;


    @JsonIgnore
    @ManyToMany(
            cascade = CascadeType.PERSIST,
            mappedBy = "authorities"
    )
    private Set<User> users;

    public Authority(){

    }

    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }


    public String getAuthority() {
        return authority;
    }

    public void setAuthority(String authority) {
        this.authority = authority;
    }
}
