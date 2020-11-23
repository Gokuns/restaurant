package com.yp.model;

import javax.persistence.*;

@Entity(name = "USERS")
public class User {
    @Id
    @Column(name = "USERNAME")
    private String userName;
    @Column(name = "PASSWORD")
    private String passWord;
    @Column(name = "ENABLED")
    private boolean enabled;


    @Transient
    private String role;

    public User(){

    }

    public User(String userName, String passWord, boolean enabled) {
        this.userName = userName;
        this.passWord = passWord;
        this.enabled = enabled;
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
    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

}
