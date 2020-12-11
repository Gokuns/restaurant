package com.yp.dto;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class UserDto {

    private String name;
    private String password;
    private boolean enabled;
    private Set<AuthorityDto> roles = new HashSet<>();

    public UserDto(){

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public Set<AuthorityDto> getRoles() {
        return roles;
    }

    public void setRoles(Set<AuthorityDto> roles) {
        this.roles = roles;
    }
}
