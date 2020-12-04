package com.yp.service;

import com.yp.controller.SellOrderController;
import com.yp.converter.SellOrderConverter;
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

    public List<SellOrder> addOrderLst(List<SellOrderDto> sellOrders) {
        List<SellOrder> sellOrderList = new ArrayList<>();
        sellOrders.forEach(sellOrderDto -> {
            SellOrder sellOrder = SellOrderConverter.convertToSellOrder(sellOrderDto);
            sellOrderList.add(sellOrder);
        });
        return sellOrderRepository.saveAll(sellOrderList);
    }

    public List<SellOrderDto> getAllOrders(){
        List<SellOrder> sellOrderList = sellOrderRepository.findAll();
        List<SellOrderDto> sellOrderDtos = new ArrayList<>();
        sellOrderList.forEach(sellOrder -> {
            SellOrderDto sellOrderDto = SellOrderConverter.convertToSellOrderDto(sellOrder);
            sellOrderDtos.add(sellOrderDto);
        });
        return sellOrderDtos;
    }
}
