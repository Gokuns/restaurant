package com.yp.controller;

import com.yp.dto.WaiterDto;
import com.yp.service.WaiterService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class WaiterControllerTest {

    @InjectMocks
    private WaiterController waiterController;

    @Mock
    private WaiterService waiterService;

    private WaiterDto waiterDto = new WaiterDto();
    private String lang = "en";


    @Before
    public void setUp() {
        waiterDto.setId(1L);
        waiterDto.setName("Goko");
        waiterDto.setSurname("Man");
        waiterDto.setDateOfBirth("2020-01-01");
        waiterDto.setPhone("000001111");
        waiterDto.setMail("asd@gmail.com");
    }

    @Test
    public void shouldgetAuthorityWithId() {
        waiterController.getUser(1L,lang);
        verify(waiterService,times(1)).getWaiter(1L,lang);
    }

    @Test
    public void getAuthorityList(){
        waiterController.getAllUsers();
        verify(waiterService,times(1)).getAllWaiters();
    }

    @Test
    public void shouldSaveWithDto() {
        waiterController.addUser(waiterDto,lang);
        verify(waiterService, times(1)).addWaiter(waiterDto,lang);

    }

    @Test
    public void shouldEditWithDtop(){
        waiterController.putUser(1L, waiterDto,lang);
        verify(waiterService, times(1)).updateWaiter(1L, waiterDto,lang);

    }

    @Test
    public void shouldDeleteWithId() {
        waiterController.deleteUser(1L,lang);
        verify(waiterService, times(1)).deleteWaiter(1L,lang);
    }

}