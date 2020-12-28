package com.yp.service;

import com.yp.builder.AuthorityBuilder;
import com.yp.builder.AuthorityDtoBuilder;
import com.yp.builder.UserBuilder;
import com.yp.builder.UserDtoBuilder;
import com.yp.dto.AuthorityDto;
import com.yp.dto.UserDto;
import com.yp.entity.Authority;
import com.yp.entity.User;
import com.yp.exception.ContentNotFoundException;
import com.yp.mapper.AuthorityMapper;
import com.yp.mapper.UserMapper;
import com.yp.repos.AuthorityRepository;
import junit.framework.TestCase;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.context.MessageSource;

import java.util.*;

import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.times;

@RunWith(MockitoJUnitRunner.class)
public class AuthorityServiceTest extends TestCase {
    @InjectMocks
    private AuthorityService authorityService;

    @Mock
    private AuthorityMapper authorityMapper;
    @Mock
    private UserMapper userMapper;

    @Mock
    private AuthorityRepository authorityRepository;
    @Mock
    private MessageSource messageSource;


    private Authority authority = new AuthorityBuilder().build();
    private AuthorityDto authorityDto = new AuthorityDtoBuilder().build();
    private List<Authority> authorityList = new ArrayList<>();
    private List<AuthorityDto> authorityDtoList = new ArrayList<>();
    private String lang = "en";



    @Before
    public void setUp() {
        authorityList.add(authority);
        authorityDtoList.add(authorityDto);
        when(authorityMapper.toDto(any())).thenReturn(authorityDto);
        when(authorityMapper.toEntity(any())).thenReturn(authority);
        when(messageSource.getMessage(any(), any(), any())).thenReturn("test");
    }

    @Test
    public void shouldgetAuthorityWithId() {
        Mockito.when(authorityRepository.findById(1L)).thenReturn(Optional.of(authority));
        AuthorityDto auth = authorityService.getRole(1L, lang);
        assertNotNull(auth);
        assertEquals(authority.getRole(), auth.getAuthority() );
    }

    @Test(expected =ContentNotFoundException.class )
    public void shoudReturnNullWithAuthorityId() {
        AuthorityDto auth = authorityService.getRole(-1L, lang);
    }


    @Test
    public void getAuthorityList(){
        Mockito.when(authorityRepository.findAll()).thenReturn(authorityList);
        List<AuthorityDto> auth = authorityService.getAllRoles();
        assertEquals(auth.get(0).getAuthority(),authorityDtoList.get(0).getAuthority());
    }

    @Test
    public void shouldSaveWithDto() {
        when(authorityRepository.save(any())).thenReturn(authority);
        Authority s = authorityService.addRole(authorityDto, lang);
        assertEquals(authority.getRole(), s.getRole() );
    }

    @Test
    public void shouldDeleteWithId() {
        authorityService.deleterRole(1L, lang);
        verify(authorityRepository, times(1)).deleteById(any());
    }

    @Test
    public void shouldEdit(){
        Mockito.when(authorityRepository.findById(any())).thenReturn(Optional.of(authority));
        authorityService.editRole(authorityDto,1L, lang);
        verify(authorityRepository, times(1)).save(any());
    }


    @Test
    public void shouldgetUsersWithId(){
        Mockito.when(authorityRepository.findById(any())).thenReturn(Optional.of(authority));
        Set<UserDto> userDtoSet =  authorityService.getUsersWithRole(1L, lang);
        assertNotNull(userDtoSet);
    }










}