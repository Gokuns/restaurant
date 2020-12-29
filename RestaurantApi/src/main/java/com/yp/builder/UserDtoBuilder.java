package com.yp.builder;

import com.yp.dto.AuthorityDto;
import com.yp.dto.UserDto;

import java.util.HashSet;
import java.util.Set;

public class UserDtoBuilder implements   Builder{
    private Long id;

    private String userName;
    private String passWord;
    private boolean enabled;
    private Set<AuthorityDto> authorities;

    public UserDtoBuilder(){
        this.id=1L;
        this.userName="";
        this.passWord="";
        this.enabled=true;
        this.authorities=new HashSet<>();
        authorities.add(new AuthorityDtoBuilder().build());
    }

    @Override
    public UserDtoBuilder withId(Long id) {
        this.id=id;
        return this;
    }

    @Override
    public UserDtoBuilder withName(String name) {
        return this;
    }

    public UserDtoBuilder withUserName(String userName) {
        this.userName=userName;
        return this;
    }
    public UserDtoBuilder withPassWord(String passWord) {
        this.passWord=passWord;
        return this;
    }
    public UserDtoBuilder withEnabled(boolean enabled) {
        this.enabled=enabled;
        return this;
    }
    public UserDtoBuilder withAuthorities(Set<AuthorityDto> authorities) {
        this.authorities=authorities;
        return this;
    }

    public UserDto build(){
        UserDto user = new UserDto();
        user.setId(this.id);
        user.setName(this.userName);
        user.setPassword(this.passWord);
        user.setEnabled(this.enabled);
        user.setAuthorities(this.authorities);
        return user;
    }

}
