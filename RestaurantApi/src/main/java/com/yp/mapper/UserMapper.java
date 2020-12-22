package com.yp.mapper;

import com.yp.dto.UserDto;
import com.yp.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel="spring")
public interface UserMapper {

    @Mapping(source = "authorities", target = "roles")
    @Mapping(source = "username", target = "name")
    UserDto toDto(User user);

    @Mapping(source = "roles", target = "authorities")
    @Mapping(source = "name", target = "username")
    User toEntity(UserDto userDto);

}
