package com.yp.builder;

import com.yp.entity.Authority;
import com.yp.entity.User;

import java.util.HashSet;
import java.util.Set;

public class AuthorityBuilder extends Builder{
    private Long _id;
    private String _authority;

    public AuthorityBuilder () {
        this._id=1L;
        this._authority="";

    }

    @Override
    public AuthorityBuilder withId(Long id) {
        this._id=id;
        return this;
    }

    @Override
    public AuthorityBuilder withName(String name) {
        return this;
    }

    public AuthorityBuilder withAuthority(String authority) {
        this._authority = authority;
        return this;
    }


    public Authority build(){
        Authority authority = new Authority();
        authority.setId(this._id);
        authority.setRole(this._authority);
        return authority;
    }
}
