package com.yp.service;

import com.yp.builder.AuthorityBuilder;
import com.yp.builder.AuthorityDtoBuilder;
import com.yp.builder.UserBuilder;
import com.yp.builder.UserDtoBuilder;
import com.yp.dto.AuthorityDto;
import com.yp.dto.TableCategoryDto;
import com.yp.dto.UserDto;
import com.yp.entity.Authority;
import com.yp.entity.TableCategory;
import com.yp.entity.User;
import com.yp.repos.UserRepository;
import junit.framework.TestCase;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceTest extends TestCase {

    @InjectMocks
    private UserService userService;

    @Mock
    private UserRepository userRepository;

    private User user = new UserBuilder().withPassWord("123456789").build();
    private UserDto editUserDto = new UserDtoBuilder().build();
    private UserDto userDto = new UserDtoBuilder().build();
    private List<User> userList = new ArrayList<>();
    private List<UserDto> userDtoList = new ArrayList<>();
    private AuthorityDto authorityDto = new AuthorityDtoBuilder().build();
    private HashSet<AuthorityDto> setDto = new HashSet<>();
    private Authority authority = new AuthorityBuilder().build();
    private HashSet<Authority> set = new HashSet<>();


    @Before
    public void setUp(){

        set.add(authority);
        setDto.add(authorityDto);
        userList.add(user);
        userDtoList.add(userDto);
    }

    @Test
    public void shouldgetWithId() {
        Mockito.when(userRepository.findById("Goko")).thenReturn(Optional.of(user));
        UserDto us = userService.getUser("Goko");
        assertNotNull(us);
        assertEquals(user.getUserName(), us.getName());
    }

    @Test
    public void shouldgetList(){
        when(userRepository.findAll()).thenReturn(userList);
        List<UserDto> lst = userService.getAllUsers();
        assertEquals(lst.get(0).getName(), userList.get(0).getUserName());
    }

    @Test
    public void shouldSaveWithDto() {
        when(userRepository.save(any())).thenReturn(user);
        User us = userService.addUser(userDto);
        assertEquals(us.getUserName(), user.getUserName());

    }

    @Test
    public void shouldEditWithId() {
        when(userRepository.findById(any())).thenReturn(Optional.of(user));
        when(userRepository.save(any())).thenReturn(user);
        User us = userService.updateUser("Goko", editUserDto);
        assertEquals(us.getPassWord(), user.getPassWord());
    }

    @Test
    public void shouldDeleteWithId() {
        userService.deleteUser("Goko");
        verify(userRepository, times(1)).deleteById(any());

    }

}