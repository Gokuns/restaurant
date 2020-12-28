package com.yp.service;

import com.yp.builder.SellOrderBuilder;
import com.yp.builder.SellOrderDtoBuilder;
import com.yp.dto.SellOrderDto;
import com.yp.entity.SellOrder;
import com.yp.mapper.SellOrderMapper;
import com.yp.repos.SellOrderRepository;
import junit.framework.TestCase;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.context.MessageSource;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class SellOrderServiceTest extends TestCase {

    @InjectMocks
    private SellOrderService sellOrderService;

    @Mock
    private SellOrderRepository sellOrderRepository;

    @Mock
    private SellOrderMapper sellOrderMapper;
    @Mock
    private MessageSource messageSource;


    private SellOrder sellOrder = new SellOrderBuilder().build();
    private SellOrderDto sellOrderDto = new SellOrderDtoBuilder().build();
    private List<SellOrder> sellOrders = new ArrayList<>();
    private List<SellOrderDto> sellOrderDtos = new ArrayList<>();
    private String lang = "en";


    @Before
    public void setUp() {
        sellOrders.add(sellOrder);
        sellOrderDtos.add(sellOrderDto);
        when(sellOrderMapper.toDto(any())).thenReturn(sellOrderDto);
        when(sellOrderMapper.toEntity(any())).thenReturn(sellOrder);
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
        List<SellOrder> lst  = sellOrderService.addOrderLst(sellOrderDtos, lang);
        assertEquals(lst.get(0).getCount(), sellOrders.get(0).getCount());

    }
}