package com.yp.converter;

import com.yp.dto.AuthorityDto;
import com.yp.dto.UserDto;
import com.yp.entity.Authority;
import com.yp.entity.User;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class UserConverter {
    public static User converToUser(UserDto userDto){
        User user = new User();
        user.setUserName(userDto.getName());
        user.setPassWord("{noop}" + userDto.getPassword());
        user.setEnabled(userDto.isEnabled());
        userDto.getRoles().forEach(authorityDto -> {
            user.getAuthorities().add(AuthorityConverter.converToAuthority(authorityDto));
        });
        return user;

    }
    public static UserDto converToUserDto(User user){
        UserDto userDto = new UserDto();
        userDto.setEnabled(user.isEnabled());
        userDto.setName(user.getUserName());
        userDto.setPassword(user.getPassWord());
        user.getAuthorities().forEach(authority -> {
            AuthorityDto authorityDto = AuthorityConverter.converToAuthorityDto(authority);
            userDto.getRoles().add(authorityDto);
        });
        return userDto;

    }
}
