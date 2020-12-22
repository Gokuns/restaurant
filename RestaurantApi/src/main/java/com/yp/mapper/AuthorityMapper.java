package com.yp.mapper;

import com.yp.dto.AuthorityDto;
import com.yp.entity.Authority;
import org.mapstruct.Mapper;

@Mapper(componentModel="spring")
public interface AuthorityMapper {

    AuthorityDto toDto(Authority authority);

    Authority toEntity(AuthorityDto authorityDto);

}
