package com.yp.mapper;

import com.yp.dto.AuthorityDto;
import com.yp.dto.CategoryDto;
import com.yp.entity.Authority;
import com.yp.entity.Category;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel="spring")
public interface AuthorityMapper {
    AuthorityDto toDto(Authority authority);

    List<AuthorityDto> toDtoList(List<Authority> authorities);

    List<Authority> toEntityList(List<AuthorityDto> authorityDtos);

    Authority toEntity(AuthorityDto authorityDto);

}
