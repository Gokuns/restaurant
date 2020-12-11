package com.yp.builder;

import com.yp.dto.AuthorityDto;
import com.yp.dto.UserDto;
import com.yp.entity.Authority;
import com.yp.entity.User;

import java.util.Set;

public class AuthorityDtoBuilder extends Builder{
    private String _authority;


    public AuthorityDtoBuilder () {
        this._authority="";
    }
    @Override
    public AuthorityDtoBuilder withId(int id) {
        return this;
    }

    @Override
    public AuthorityDtoBuilder withName(String name) {
        return this;
    }

    public AuthorityDtoBuilder withAuthority(String authority) {
        this._authority = authority;
        return this;
    }

    public AuthorityDto build(){
        AuthorityDto authority = new AuthorityDto();
        authority.setAuthority(this._authority);
        return authority;
    }
}
