package com.yp.mapper;

import com.yp.dto.WaiterDto;
import com.yp.entity.Waiter;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel="spring")
public interface WaiterMapper {

    @Mapping(source = "media", target = "media")
    WaiterDto toDto(Waiter waiter);

    @Mapping(source = "media", target = "media")
    Waiter toEntity(WaiterDto waiterDto);
}
