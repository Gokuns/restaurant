package com.yp.service;

import com.yp.builder.AuthorityBuilder;
import com.yp.builder.AuthorityDtoBuilder;
import com.yp.dto.AuthorityDto;
import com.yp.dto.UserDto;
import com.yp.entity.Authority;
import com.yp.entity.User;
import com.yp.mapper.AuthorityMapper;
import com.yp.repos.AuthorityRepository;
import junit.framework.TestCase;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.*;

import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.times;

@RunWith(MockitoJUnitRunner.class)
public class AuthorityServiceTest extends TestCase {
    @InjectMocks
    private AuthorityService authorityService;

    @Mock
    private AuthorityMapper authorityMapper;

    @Mock
    private AuthorityRepository authorityRepository;

    private Authority authority = new AuthorityBuilder().build();
    private AuthorityDto authorityDto = new AuthorityDtoBuilder().build();
    private List<Authority> authorityList = new ArrayList<>();
    private List<AuthorityDto> authorityDtoList = new ArrayList<>();



    @Before
    public void setUp() {
        authorityList.add(authority);
        authorityDtoList.add(authorityDto);
        when(authorityMapper.toDto(any())).thenReturn(authorityDto);
        when(authorityMapper.toEntity(any())).thenReturn(authority);
    }

    @Test
    public void shouldgetAuthorityWithId() {
        Mockito.when(authorityRepository.findById(1)).thenReturn(Optional.of(authority));
        AuthorityDto auth = authorityService.getRole(1);
        assertNotNull(auth);
        assertEquals(authority.getAuthority(), auth.getAuthority() );
    }

    @Test
    public void shoudReturnNullWithAuthorityId() {
        AuthorityDto auth = authorityService.getRole(1);
        assertNull(auth);
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
        Authority s = authorityService.addRole(authorityDto);
        assertEquals(authority.getAuthority(), s.getAuthority() );
    }

    @Test
    public void shouldDeleteWithId() {
        authorityService.deleterRole(1);
        verify(authorityRepository, times(1)).deleteById(any());
    }

    @Test
    public void shouldEdit(){
        Mockito.when(authorityRepository.findById(any())).thenReturn(Optional.of(authority));authorityService.editRole(authorityDto,1);
        verify(authorityRepository, times(1)).saveAndFlush(any());
    }


    @Test
    public void shouldgetUsersWithId(){
        Mockito.when(authorityRepository.findById(any())).thenReturn(Optional.of(authority));
        Set<UserDto> userDtoSet =  authorityService.getUsersWithRole(1);
        assertNotNull(userDtoSet);
    }










}