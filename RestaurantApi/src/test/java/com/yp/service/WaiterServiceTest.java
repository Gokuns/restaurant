package com.yp.service;

import com.yp.builder.WaiterBuilder;
import com.yp.builder.WaiterDtoBuilder;
import com.yp.dto.UserDto;
import com.yp.dto.WaiterDto;
import com.yp.entity.User;
import com.yp.entity.Waiter;
import com.yp.repos.WaiterRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class WaiterServiceTest {

    @InjectMocks
    private WaiterService waiterService;

    @Mock
    private WaiterRepository waiterRepository;

    private Waiter waiter = new WaiterBuilder().build();
    private WaiterDto waiterDto = new WaiterDtoBuilder().build();
    private List<Waiter> waiters = new ArrayList<>();
    private List<WaiterDto> waiterDtos = new ArrayList<>();



    @Before
    public void setUp() throws Exception {
        waiters.add(waiter);
        waiterDtos.add(waiterDto);
    }

    @Test
    public void shouldgetWithId() {
        Mockito.when(waiterRepository.findById(any())).thenReturn(Optional.of(waiter));
        WaiterDto us = waiterService.getWaiter(Long.valueOf(1));
        assertNotNull(us);
        assertEquals(waiter.getName(), us.getName());
    }

    @Test
    public void shouldgetList(){
        when(waiterRepository.findAll()).thenReturn(waiters);
        List<WaiterDto> lst = waiterService.getAllWaiters();
        assertEquals(lst.get(0).getName(), waiterDtos.get(0).getName());
    }

    @Test
    public void shouldSaveWithDto() {
        when(waiterRepository.save(any())).thenReturn(waiter);
        Waiter us = waiterService.addWaiter(waiterDto);
        assertEquals(us.getName(), waiter.getName());

    }

    @Test
    public void shouldEditWithId() {
        when(waiterRepository.findById(any())).thenReturn(Optional.of(waiter));
        when(waiterRepository.save(any())).thenReturn(waiter);
        Waiter us = waiterService.updateWaiter(Long.valueOf(1), waiterDto);
        assertEquals(us.getName(), waiter.getName());
    }

    @Test
    public void shouldDeleteWithId() {
        waiterService.deleteWaiter(Long.valueOf(1));
        verify(waiterRepository, times(1)).deleteById(any());

    }


}