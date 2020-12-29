package com.yp.mapper;

import com.yp.dto.UserDto;
import com.yp.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel="spring")
public interface UserMapper {

    @Mapping(source = "username", target = "name")
    UserDto toDto(User user);

    @Mapping(source = "name", target = "username")
    User toEntity(UserDto userDto);

}
