package com.yp.service;

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

    private User user = new User();
    private UserDto editUserDto = new UserDto();
    private UserDto userDto = new UserDto();
    private List<User> userList = new ArrayList<>();
    private List<UserDto> userDtoList = new ArrayList<>();
    private AuthorityDto authorityDto = new AuthorityDto();
    private HashSet<AuthorityDto> setDto = new HashSet<>();
    private Authority authority = new Authority();
    private HashSet<Authority> set = new HashSet<>();


    @Before
    public void setUp(){
        authority.setUsers(new HashSet<>());
        authority.setAuthority("Goko");
        authorityDto.setAuthority("Goko");

        set.add(authority);
        setDto.add(authorityDto);

        user.setUserName("Goko");
        user.setPassWord("123");
        user.setEnabled(true);
        user.setAuthorities(set);
        userList.add(user);

        editUserDto.setName("Goko");
        editUserDto.setPassword("246");
        editUserDto.setEnabled(true);
        editUserDto.setRoles(new HashSet<>());


        userDto.setName("Goko");
        userDto.setPassword("123");
        userDto.setEnabled(true);
        userDto.setRoles(setDto);
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