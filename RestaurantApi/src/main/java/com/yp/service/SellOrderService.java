package com.yp.service;

import com.yp.dto.SellOrderDto;
import com.yp.entity.SellOrder;
import com.yp.repos.SellOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SellOrderService {
    @Autowired
    private SellOrderRepository sellOrderRepository;

    public void addOrderLst(List<SellOrderDto> sellOrders) {
        List<SellOrder> sellOrderList = new ArrayList<>();
        for(SellOrderDto sellOrderDto:sellOrders){
            SellOrder sellOrder = new SellOrder();
            sellOrder.setOrderId(sellOrderDto.getOrderId());
            sellOrder.setProductId(sellOrderDto.getProductId());
            sellOrder.setCount(sellOrderDto.getCount());
            sellOrder.setTotalPrice(sellOrderDto.getTotalPrice());
            sellOrderList.add(sellOrder);
        }
        sellOrderRepository.saveAll(sellOrderList);
    }

    public List<SellOrderDto> getAllOrders(){
        List<SellOrder> sellOrderList = sellOrderRepository.findAll();
        List<SellOrderDto> sellOrderDtos = new ArrayList<>();
        for(SellOrder sellOrder:sellOrderList){
            SellOrderDto sellOrderDto = new SellOrderDto();
            sellOrderDto.setOrderId(sellOrder.getOrderId());
            sellOrderDto.setProductId(sellOrder.getProductId());
            sellOrderDto.setCount(sellOrder.getCount());
            sellOrderDto.setTotalPrice(sellOrder.getTotalPrice());
            sellOrderDto.setCreateDate(sellOrder.getCreationDateTime());
            sellOrderDtos.add(sellOrderDto);
        }
        return sellOrderDtos;
    }
}
