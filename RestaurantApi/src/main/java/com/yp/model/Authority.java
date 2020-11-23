package com.yp.model;

import javax.persistence.*;

@Entity(name = "AUTHORITIES")
public class Authority {


    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "ID")
    int id;
    @Column(name= "USERNAME")
    private String username;
    @Column(name= "AUTHORITY")
    private String authority;

    public Authority(){

    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getAuthority() {
        return authority;
    }

    public void setAuthority(String authority) {
        this.authority = authority;
    }
}
