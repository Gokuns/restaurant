package com.yp.builder;

import com.yp.dto.AuthorityDto;
public class AuthorityDtoBuilder implements Builder{
    private Long id;
    private String authority;


    public AuthorityDtoBuilder () {
        this.id=1L;
        this.authority="";
    }
    @Override
    public AuthorityDtoBuilder withId(Long id) {
        this.id=id;
        return this;
    }

    @Override
    public AuthorityDtoBuilder withName(String name) {
        return this;
    }

    public AuthorityDtoBuilder withAuthority(String authority) {
        this.authority = authority;
        return this;
    }

    public AuthorityDto build(){
        AuthorityDto auth = new AuthorityDto();
        auth.setId(this.id);
        auth.setRole(this.authority);
        return auth;
    }
}
