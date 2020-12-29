package com.yp.mapper;

import com.yp.dto.AuthorityDto;
import com.yp.entity.Authority;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel="spring")
public interface AuthorityMapper {
    AuthorityDto toDto(Authority authority);

    List<AuthorityDto> toDtoList(List<Authority> authorities);

    List<Authority> toEntityList(List<AuthorityDto> authorityDtos);

    Authority toEntity(AuthorityDto authorityDto);

}
