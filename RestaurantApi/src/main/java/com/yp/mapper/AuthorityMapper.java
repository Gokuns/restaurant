package com.yp.mapper;

import com.yp.dto.AuthorityDto;
import com.yp.entity.Authority;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel="spring")
public interface AuthorityMapper {
    @Mapping(source = "role", target = "authority")
    AuthorityDto toDto(Authority authority);

    @Mapping(source = "authority", target = "role")
    Authority toEntity(AuthorityDto authorityDto);

}
