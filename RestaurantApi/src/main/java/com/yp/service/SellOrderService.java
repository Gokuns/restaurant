package com.yp.service;


import com.yp.dto.SellOrderDto;
import com.yp.entity.SellOrder;
import com.yp.mapper.SellOrderMapper;
import com.yp.repos.SellOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SellOrderService {
    @Autowired
    private SellOrderRepository sellOrderRepository;

    @Autowired
    private SellOrderMapper sellOrderMapper;


    public List<SellOrder> addOrderLst(List<SellOrderDto> sellOrders) {
        List<SellOrder> sellOrderList = new ArrayList<>();
        sellOrders.forEach(sellOrderDto -> {
            SellOrder sellOrder = sellOrderMapper.toEntity(sellOrderDto);
            sellOrderList.add(sellOrder);
        });
        return sellOrderRepository.saveAll(sellOrderList);
    }

    public List<SellOrderDto> getAllOrders(){
        List<SellOrder> sellOrderList = sellOrderRepository.findAll();
        List<SellOrderDto> sellOrderDtos = new ArrayList<>();
        sellOrderList.forEach(sellOrder -> {
            SellOrderDto sellOrderDto = sellOrderMapper.toDto(sellOrder);
            sellOrderDtos.add(sellOrderDto);
        });
        return sellOrderDtos;
    }
}
