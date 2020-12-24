package com.yp.builder;

import com.yp.dto.AuthorityDto;
import com.yp.dto.UserDto;
import com.yp.entity.Authority;
import com.yp.entity.User;

import java.util.Set;

public class AuthorityDtoBuilder extends Builder{
    private Long _id;
    private String _authority;


    public AuthorityDtoBuilder () {
        this._id=1L;
        this._authority="";
    }
    @Override
    public AuthorityDtoBuilder withId(Long id) {
        this._id=id;
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
        authority.setId(this._id);
        authority.setAuthority(this._authority);
        return authority;
    }
}
