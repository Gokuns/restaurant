package com.yp.controller;

import com.yp.builder.UserDtoBuilder;
import com.yp.dto.UserDto;
import com.yp.service.UserService;
import junit.framework.TestCase;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class UserControllerTest  extends TestCase {

    @InjectMocks
    private UserController userController;

    @Mock
    private UserService userService;

    private UserDto userDto = new UserDtoBuilder().build();
    private String lang = "en";

    @Before
    public void setUp() {
    }

    @Test
    public void shouldgetAuthorityWithId() {
        userController.getUser(1L, lang);
        verify(userService,times(1)).getUser(1L, lang);
    }

    @Test
    public void getAuthorityList(){
        userController.getAllUsers();
        verify(userService,times(1)).getAllUsers();
    }

    @Test
    public void shouldSaveWithDto() {
        userController.addUser(userDto, lang);
        verify(userService, times(1)).addUser(userDto, lang);

    }

    @Test
    public void shouldEditWithDtop(){
        userController.putUser(1L, userDto, lang);
        verify(userService, times(1)).updateUser(1L, userDto, lang);

    }

    @Test
    public void shouldDeleteWithId() {
        userController.deleteUser(1L, lang);
        verify(userService, times(1)).deleteUser(1L, lang);
    }

}