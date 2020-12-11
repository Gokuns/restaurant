package com.yp.service;

import com.yp.builder.SellOrderBuilder;
import com.yp.builder.SellOrderDtoBuilder;
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

    SellOrder sellOrder = new SellOrderBuilder().build();
    SellOrderDto sellOrderDto = new SellOrderDtoBuilder().build();
    List<SellOrder> sellOrders = new ArrayList<>();
    List<SellOrderDto> sellOrderDtos = new ArrayList<>();


    @Before
    public void setUp() {
        sellOrders.add(sellOrder);
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