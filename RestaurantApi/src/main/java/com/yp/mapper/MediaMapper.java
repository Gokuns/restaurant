package com.yp.mapper;

import com.yp.dto.MediaDto;
import com.yp.entity.Media;
import org.mapstruct.Mapper;

@Mapper(componentModel="spring")
public interface MediaMapper {

    MediaDto toDto(Media media);

    Media toEntity(MediaDto mediaDto);

}
