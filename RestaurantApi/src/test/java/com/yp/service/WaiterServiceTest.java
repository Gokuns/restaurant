package com.yp.service;

import com.yp.builder.MediaBuilder;
import com.yp.builder.WaiterBuilder;
import com.yp.builder.WaiterDtoBuilder;
import com.yp.dto.WaiterDto;
import com.yp.entity.Media;
import com.yp.entity.Waiter;
import com.yp.mapper.MediaMapper;
import com.yp.mapper.WaiterMapper;
import com.yp.repos.WaiterRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.context.MessageSource;
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

    @Mock
    private WaiterMapper waiterMapper;
    @Mock
    private MediaMapper mediaMapper;
    @Mock
    private MessageSource messageSource;


    private Waiter waiter = new WaiterBuilder().build();
    private WaiterDto waiterDto = new WaiterDtoBuilder().build();
    private List<Waiter> waiters = new ArrayList<>();
    private List<WaiterDto> waiterDtos = new ArrayList<>();
    private Media media = new MediaBuilder().build();
    private String lang = "en";



    @Before
    public void setUp() {
        waiters.add(waiter);
        waiterDtos.add(waiterDto);
        when(waiterMapper.toDto(any())).thenReturn(waiterDto);
        when(waiterMapper.toEntity(any())).thenReturn(waiter);
//        when(mediaMapper.toEntity(any())).thenReturn(media);
    }

    @Test
    public void shouldgetWithId() {
        Mockito.when(waiterRepository.findById(any())).thenReturn(Optional.of(waiter));
        WaiterDto us = waiterService.getWaiter(1L, lang);
        assertNotNull(us);
        assertEquals(waiter.getName(), us.getName());
    }

    @Test
    public void shouldgetList(){
        when(waiterRepository.findAll()).thenReturn(waiters);
        List<WaiterDto> lst = waiterService.getAllWaiters( );
        assertEquals(lst.get(0).getName(), waiterDtos.get(0).getName());
    }

    @Test
    public void shouldSaveWithDto() {
        when(waiterRepository.save(any())).thenReturn(waiter);
        Waiter us = waiterService.addWaiter(waiterDto, lang);
        assertEquals(us.getName(), waiter.getName());

    }

    @Test
    public void shouldEditWithId() {
        when(waiterRepository.findById(any())).thenReturn(Optional.of(waiter));
        when(waiterRepository.save(any())).thenReturn(waiter);
        Waiter us = waiterService.updateWaiter(1L, waiterDto, lang);
        assertEquals(us.getName(), waiter.getName());
    }

    @Test
    public void shouldDeleteWithId() {
        waiterService.deleteWaiter(1L, lang);
        verify(waiterRepository, times(1)).deleteById(any());

    }


}