package com.yp.service;

import com.yp.dto.SellOrderDto;
import com.yp.dto.UserDto;
import com.yp.entity.SellOrder;
import com.yp.entity.User;
import com.yp.repos.SellOrderRepository;
import junit.framework.TestCase;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class SellOrderServiceTest extends TestCase {

    @InjectMocks
    SellOrderService sellOrderService;

    @Mock
    SellOrderRepository sellOrderRepository;

    SellOrder sellOrder = new SellOrder();
    SellOrderDto sellOrderDto = new SellOrderDto();
    List<SellOrder> sellOrders = new ArrayList<>();
    List<SellOrderDto> sellOrderDtos = new ArrayList<>();


    @Before
    public void setUp() {
        sellOrder.setTotalPrice(100);
        sellOrder.setCount(10);
        sellOrder.setProductId(2);
        sellOrder.setOrderId(1);
        sellOrders.add(sellOrder);

        sellOrderDto.setTotalPrice(100);
        sellOrderDto.setCount(10);
        sellOrderDto.setProductId(2);
        sellOrderDto.setOrderId(1);
        sellOrderDtos.add(sellOrderDto);


    }

    @Test
    public void shouldgetList(){
        when(sellOrderRepository.findAll()).thenReturn(sellOrders);
        List<SellOrderDto> lst = sellOrderService.getAllOrders();
        assertEquals(lst.get(0).getCount(), sellOrders.get(0).getCount());
    }

    @Test
    public void shouldSaveWithDto() {
        when(sellOrderRepository.saveAll(any())).thenReturn(sellOrders);
        List<SellOrder> lst  = sellOrderService.addOrderLst(sellOrderDtos);
        assertEquals(lst.get(0).getCount(), sellOrders.get(0).getCount());

    }
}