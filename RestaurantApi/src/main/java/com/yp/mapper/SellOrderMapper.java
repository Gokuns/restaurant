package com.yp.mapper;

import com.yp.dto.SellOrderDto;
import com.yp.entity.SellOrder;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel="spring")
public interface SellOrderMapper {

//    SellOrderMapper INSTANCE = Mappers.getMapper(SellOrderMapper.class);

    SellOrderDto toDto(SellOrder sellOrder);

    SellOrder toEntity(SellOrderDto sellOrderDto);
}
