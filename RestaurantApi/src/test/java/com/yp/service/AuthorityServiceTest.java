package com.yp.service;

import com.yp.dto.AuthorityDto;
import com.yp.entity.Authority;
import com.yp.repos.AuthorityRepository;
import junit.framework.TestCase;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
    private AuthorityRepository authorityRepository;

    private Authority authority = new Authority();
    private AuthorityDto authorityDto = new AuthorityDto();
    private List<Authority> authorityList = new ArrayList<>();
    private List<AuthorityDto> authorityDtoList = new ArrayList<>();



    @Before
    public void setUp() {
        authority.setAuthority("ROLE_ADMIN");
        authorityDto.setAuthority("ROLE_ADMIN");
        authorityList.add(authority);
        authorityDtoList.add(authorityDto);
    }

    @Test
    public void shouldgetAuthorityWithId() {
        Mockito.when(authorityRepository.findById("ROLE_ADMIN")).thenReturn(Optional.of(authority));
        AuthorityDto auth = authorityService.getRole("ROLE_ADMIN");
        assertNotNull(auth);
        assertEquals(authority.getAuthority(), auth.getAuthority() );
    }

    @Test
    public void shoudReturnNullWithAuthorityId() {
        AuthorityDto auth = authorityService.getRole("GOKO");
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
        authorityService.deleterRole("Goko");
        verify(authorityRepository, times(1)).deleteById(any());

    }










}