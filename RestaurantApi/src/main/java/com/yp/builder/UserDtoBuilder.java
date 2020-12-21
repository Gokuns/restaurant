package com.yp.builder;

import com.yp.dto.AuthorityDto;
import com.yp.dto.UserDto;
import com.yp.entity.Authority;
import com.yp.entity.User;

import java.util.HashSet;
import java.util.Set;

public class UserDtoBuilder extends  Builder{
    private int _id;

    private String _userName;
    private String _passWord;
    private boolean _enabled;
    private Set<AuthorityDto> _authorities;

    public UserDtoBuilder(){
        this._id=1;
        this._userName="";
        this._passWord="";
        this._enabled=true;
        this._authorities=new HashSet<>();
        _authorities.add(new AuthorityDtoBuilder().build());
    }

    @Override
    public UserDtoBuilder withId(int id) {
        this._id=id;
        return this;
    }

    @Override
    public UserDtoBuilder withName(String name) {
        return this;
    }

    public UserDtoBuilder withUserName(String userName) {
        this._userName=userName;
        return this;
    }
    public UserDtoBuilder withPassWord(String passWord) {
        this._passWord=passWord;
        return this;
    }
    public UserDtoBuilder withEnabled(boolean enabled) {
        this._enabled=enabled;
        return this;
    }
    public UserDtoBuilder withAuthorities(Set<AuthorityDto> authorities) {
        this._authorities=authorities;
        return this;
    }

    public UserDto build(){
        UserDto user = new UserDto();
        user.setId(this._id);
        user.setName(this._userName);
        user.setPassword(this._passWord);
        user.setEnabled(this._enabled);
        user.setRoles(this._authorities);
        return user;
    }

}
