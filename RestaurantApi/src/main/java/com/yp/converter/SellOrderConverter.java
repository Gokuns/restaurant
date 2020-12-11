package com.yp.converter;

import com.yp.dto.SellOrderDto;
import com.yp.entity.SellOrder;

public class SellOrderConverter {
    public static SellOrder convertToSellOrder(SellOrderDto sellOrderDto) {
        SellOrder sellOrder = new SellOrder();
        sellOrder.setOrderId(sellOrderDto.getOrderId());
        sellOrder.setProductId(sellOrderDto.getProductId());
        sellOrder.setCount(sellOrderDto.getCount());
        sellOrder.setTotalPrice(sellOrderDto.getTotalPrice());
        sellOrder.setTableName(sellOrderDto.getTableName());
        sellOrder.setWaiterName(sellOrderDto.getWaiterName());
        return sellOrder;
    }
    public static SellOrderDto convertToSellOrderDto(SellOrder sellOrder){
        SellOrderDto sellOrderDto = new SellOrderDto();
        sellOrderDto.setOrderId(sellOrder.getOrderId());
        sellOrderDto.setProductId(sellOrder.getProductId());
        sellOrderDto.setCount(sellOrder.getCount());
        sellOrderDto.setTotalPrice(sellOrder.getTotalPrice());
        sellOrderDto.setCreateDate(sellOrder.getCreateDate());
        sellOrderDto.setTableName(sellOrder.getTableName());
        sellOrderDto.setWaiterName(sellOrder.getWaiterName());
        return sellOrderDto;
    }
}
