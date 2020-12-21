package com.yp.converter;

import com.yp.dto.AuthorityDto;
import com.yp.entity.Authority;

public class AuthorityConverter {
    public static Authority converToAuthority(AuthorityDto authorityDto){
        Authority authority = new Authority();
        authority.setId(authorityDto.getId());
        authority.setAuthority(authorityDto.getAuthority());
        return authority;
    }
    public static AuthorityDto converToAuthorityDto(Authority authority){
        AuthorityDto authorityDto = new AuthorityDto();
        authorityDto.setId(authority.getId());
        authorityDto.setAuthority(authority.getAuthority());
        return authorityDto;
    }
}
