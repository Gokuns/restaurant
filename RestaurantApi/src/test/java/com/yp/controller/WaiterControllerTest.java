package com.yp.controller;

import com.yp.dto.WaiterDto;
import com.yp.service.UserService;
import com.yp.service.WaiterService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.junit.Assert.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class WaiterControllerTest {

    @InjectMocks
    private WaiterController waiterController;

    @Mock
    private WaiterService waiterService;

    private WaiterDto waiterDto = new WaiterDto();


    @Before
    public void setUp() throws Exception {
        waiterDto.setId(1L);
        waiterDto.setName("Goko");
        waiterDto.setSurname("Man");
        waiterDto.setDateOfBirth("2020-01-01");
        waiterDto.setPhone("000001111");
        waiterDto.setMail("asd@gmail.com");
    }

    @Test
    public void shouldgetAuthorityWithId() {
        waiterController.getUser(1L);
        verify(waiterService,times(1)).getWaiter(1L);
    }

    @Test
    public void getAuthorityList(){
        waiterController.getAllUsers();
        verify(waiterService,times(1)).getAllWaiters();
    }

    @Test
    public void shouldSaveWithDto() {
        waiterController.addUser(waiterDto);
        verify(waiterService, times(1)).addWaiter(waiterDto);

    }

    @Test
    public void shouldEditWithDtop(){
        waiterController.putUser(1L, waiterDto);
        verify(waiterService, times(1)).updateWaiter(1L, waiterDto);

    }

    @Test
    public void shouldDeleteWithId() {
        waiterController.deleteUser(1L);
        verify(waiterService, times(1)).deleteWaiter(1L);
    }

}