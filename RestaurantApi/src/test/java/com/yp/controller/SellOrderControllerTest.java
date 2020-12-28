package com.yp.controller;

import com.yp.dto.SellOrderDto;
import com.yp.service.SellOrderService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class SellOrderControllerTest {

    @InjectMocks
    private SellOrderController sellOrderController;

    @Mock
    private SellOrderService sellOrderService;

    SellOrderDto sellOrderDto = new SellOrderDto();
    List<SellOrderDto> lst = new ArrayList<>();
    private String lang = "en";

    @Before
    public void setUp() {

    }

    @Test
    public void shouldSaveWithDto() {
        sellOrderController.addOrder(lst, lang);
        verify(sellOrderService, times(1)).addOrderLst(lst, lang);

    }
    @Test
    public void getAuthorityList(){
        sellOrderController.getAllOrders();
        verify(sellOrderService,times(1)).getAllOrders();
    }

}