package com.yp.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Entity(name = "USERS")
public class User implements Serializable {
    @Id
    @Column(name = "USERNAME" , unique = true)
    private String userName;
    @Column(name = "PASSWORD")
    private String passWord;
    @Column(name = "ENABLED")
    private boolean enabled;

    @ManyToMany(cascade = {CascadeType.PERSIST})
    @JoinTable(
            name = "AUTHORITIES",
            joinColumns = {@JoinColumn(name = "USERNAME")},
            inverseJoinColumns = {@JoinColumn(name = "AUTHORITY")}

    )
    private Set<Authority> authorities;

    public User(){

    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }
    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public Set<Authority> getAuthorities() {
        return authorities;
    }

    public void setAuthorities(Set<Authority> authorities) {
        this.authorities = authorities;
    }


}
