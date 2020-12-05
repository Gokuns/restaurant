package com.yp.controller;

import com.yp.dto.UserDto;
import com.yp.service.UserService;
import junit.framework.TestCase;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.HashSet;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class UserControllerTest  extends TestCase {

    @InjectMocks
    private UserController userController;

    @Mock
    private UserService userService;

    private UserDto userDto = new UserDto();

    @Before
    public void setUp() {
        userDto.setRoles(new HashSet<>());
        userDto.setName("Goko");
        userDto.setPassword("123");
        userDto.setEnabled(true);
    }

    @Test
    public void shouldgetAuthorityWithId() {
        userController.getUser("Goko");
        verify(userService,times(1)).getUser("Goko");
    }

    @Test
    public void getAuthorityList(){
        userController.getAllUsers();
        verify(userService,times(1)).getAllUsers();
    }

    @Test
    public void shouldSaveWithDto() {
        userController.addUser(userDto);
        verify(userService, times(1)).addUser(userDto);

    }

    @Test
    public void shouldEditWithDtop(){
        userController.putUser("Goko", userDto);
        verify(userService, times(1)).updateUser("Goko", userDto);

    }

    @Test
    public void shouldDeleteWithId() {
        userController.deleteUser("Goko");
        verify(userService, times(1)).deleteUser("Goko");
    }

}