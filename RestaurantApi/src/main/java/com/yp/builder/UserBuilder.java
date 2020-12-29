package com.yp.builder;

import com.yp.entity.Authority;
import com.yp.entity.User;

import java.util.HashSet;
import java.util.Set;

public class UserBuilder implements Builder{

    private Long id;
    private String userName;
    private String passWord;
    private boolean enabled;
    private Set<Authority> authorities;

    public UserBuilder(){
        this.id=1L;
        this.userName="";
        this.passWord="";
        this.enabled=true;
        this.authorities=new HashSet<>();
        authorities.add(new AuthorityBuilder().build());
    }

    @Override
    public UserBuilder withId(Long id) {
        this.id=id;
        return this;
    }

    @Override
    public UserBuilder withName(String name) {
        return this;
    }

    public UserBuilder withUserName(String userName) {
        this.userName=userName;
        return this;
    }
    public UserBuilder withPassWord(String passWord) {
        this.passWord=passWord;
        return this;
    }
    public UserBuilder withEnabled(boolean enabled) {
        this.enabled=enabled;
        return this;
    }
    public UserBuilder withAuthorities(Set<Authority> authorities) {
        this.authorities=authorities;
        return this;
    }

    public User build(){
        User user = new User();
        user.setId(this.id);
        user.setUsername(this.userName);
        user.setPassword(this.passWord);
        user.setEnabled(this.enabled);
        user.setAuthorities(this.authorities);
        return user;
    }


}
