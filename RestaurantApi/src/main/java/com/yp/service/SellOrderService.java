package com.yp.service;


import com.yp.dto.SellOrderDto;
import com.yp.entity.SellOrder;
import com.yp.exception.BusinessRuleException;
import com.yp.mapper.SellOrderMapper;
import com.yp.repos.SellOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

@Service
public class SellOrderService {
    @Autowired
    private SellOrderRepository sellOrderRepository;

    @Autowired
    private SellOrderMapper sellOrderMapper;
    @Autowired
    private MessageSource messageSource;


    public List<SellOrder> addOrderLst(List<SellOrderDto> sellOrders, String lang) {
        if(sellOrders==null){
            throw new BusinessRuleException(messageSource.getMessage("BusinessRuleException", new Object[0], new Locale(lang)));
        }
        List<SellOrder> sellOrderList = sellOrders.stream().map(sellOrderMapper::toEntity).collect(Collectors.toList());
        return sellOrderRepository.saveAll(sellOrderList);
    }

    public List<SellOrderDto> getAllOrders(){
       return  sellOrderRepository.findAll().stream().map(sellOrderMapper::toDto).collect(Collectors.toList());
    }
}
