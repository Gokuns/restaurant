package com.yp.builder;

import com.yp.entity.Authority;
import com.yp.entity.User;

import java.util.HashSet;
import java.util.Set;

public class UserBuilder extends Builder{

    private int _id;
    private String _userName;
    private String _passWord;
    private boolean _enabled;
    private Set<Authority> _authorities;

    public UserBuilder(){
        this._id=1;
        this._userName="";
        this._passWord="";
        this._enabled=true;
        this._authorities=new HashSet<>();
        _authorities.add(new AuthorityBuilder().build());
    }

    @Override
    public UserBuilder withId(int id) {
        this._id=id;
        return this;
    }

    @Override
    public UserBuilder withName(String name) {
        return this;
    }

    public Builder withUserName(String userName) {
        this._userName=userName;
        return this;
    }
    public UserBuilder withPassWord(String passWord) {
        this._passWord=passWord;
        return this;
    }
    public UserBuilder withEnabled(boolean enabled) {
        this._enabled=enabled;
        return this;
    }
    public UserBuilder withAuthorities(Set<Authority> authorities) {
        this._authorities=authorities;
        return this;
    }

    public User build(){
        User user = new User();
        user.setId(this._id);
        user.setUserName(this._userName);
        user.setPassWord(this._passWord);
        user.setEnabled(this._enabled);
        user.setAuthorities(this._authorities);
        return user;
    }


}
