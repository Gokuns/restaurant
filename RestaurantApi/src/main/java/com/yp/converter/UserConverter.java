package com.yp.converter;

import com.yp.dto.AuthorityDto;
import com.yp.dto.UserDto;
import com.yp.entity.Authority;
import com.yp.entity.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class UserConverter {
    private static final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    public static User converToUser(UserDto userDto){
        User user = new User();
        user.setUsername(userDto.getName());
        String pw = encoder.encode(userDto.getPassword());
        user.setPassword(pw);
        user.setEnabled(userDto.isEnabled());
        Set<Authority> authorities = new HashSet<>();
        userDto.getRoles().forEach(authorityDto -> {
            authorities.add(AuthorityConverter.converToAuthority(authorityDto));
        });
        user.setAuthorities(authorities);
        return user;

    }
    public static UserDto converToUserDto(User user){
        UserDto userDto = new UserDto();
        userDto.setId(user.getId());
        userDto.setEnabled(user.isEnabled());
        userDto.setName(user.getUsername());
        String pw = user.getPassword();
        userDto.setPassword(pw);
        Set<AuthorityDto> authorityDtos = new HashSet<>();
        user.getAuthorities().forEach(authority -> {
            AuthorityDto authorityDto = AuthorityConverter.converToAuthorityDto(authority);
            authorityDtos.add(authorityDto);
        });
        userDto.setRoles(authorityDtos);
        return userDto;

    }
}
