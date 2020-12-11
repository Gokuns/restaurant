package com.yp.builder;

import com.yp.entity.Authority;
import com.yp.entity.User;

import java.util.HashSet;
import java.util.Set;

public class AuthorityBuilder extends Builder{
    private String _authority;
    private Set<User> _users;

    public AuthorityBuilder () {
        this._authority="";
        this._users= new HashSet<>();

    }

    @Override
    public AuthorityBuilder withId(int id) {
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

    public AuthorityBuilder withUsers(Set<User> users) {
        this._users = users;
        return this;
    }

    public Authority build(){
        Authority authority = new Authority();
        authority.setAuthority(this._authority);
        authority.setUsers(this._users);
        return authority;
    }
}
