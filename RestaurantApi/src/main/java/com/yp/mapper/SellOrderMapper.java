package com.yp.mapper;

import com.yp.dto.SellOrderDto;
import com.yp.entity.SellOrder;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface SellOrderMapper {

//    SellOrderMapper INSTANCE = Mappers.getMapper(SellOrderMapper.class);

    @Mapping(source = "customerId", target = "customerId")
    SellOrderDto toDto(SellOrder sellOrder);

    @Mapping(source = "customerId", target = "customerId")
    SellOrder toEntity(SellOrderDto sellOrderDto);
}
