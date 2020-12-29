package com.yp.builder;

import com.yp.entity.Authority;
public class AuthorityBuilder implements Builder{
    private Long id;
    private String authority;

    public AuthorityBuilder () {
        this.id=1L;
        this.authority="";

    }

    @Override
    public AuthorityBuilder withId(Long id) {
        this.id=id;
        return this;
    }

    @Override
    public AuthorityBuilder withName(String name) {
        return this;
    }

    public AuthorityBuilder withAuthority(String authority) {
        this.authority = authority;
        return this;
    }


    public Authority build(){
        Authority auth = new Authority();
        auth.setId(this.id);
        auth.setRole(this.authority);
        return auth;
    }
}
