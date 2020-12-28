package com.yp.controller;

import com.yp.dto.AuthorityDto;
import com.yp.service.AuthorityService;
import junit.framework.TestCase;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import static org.mockito.Mockito.*;
@RunWith(MockitoJUnitRunner.class)
public class AuthorityControllerTest extends TestCase {

    @InjectMocks
    private AuthorityController authorityController;

    @Mock
    private AuthorityService authorityService;

    private AuthorityDto authorityDto = new AuthorityDto();
    private String lang = "en";

    @Before
    public void setUp() {
        authorityDto.setAuthority("ROLE_ADMIN");

    }

    @Test
    public void shouldgetAuthorityWithId() {
        authorityController.getRole(1L, lang);
        verify(authorityService,times(1)).getRole(1L, lang);
    }


    @Test
    public void getAuthorityList(){
        authorityController.getAllRoles();
        verify(authorityService,times(1)).getAllRoles();
 }

    @Test
    public void shouldSaveWithDto() {
        authorityController.addRole(authorityDto, lang);
        verify(authorityService, times(1)).addRole(authorityDto, lang);

 }


    @Test
    public void shouldEditWithDto() {
        authorityController.editRole(authorityDto, 1L, lang);
        verify(authorityService, times(1)).editRole(authorityDto,1L, lang);

    }

    @Test
    public void shouldDeleteWithId() {
        authorityController.deleteRole(1L,lang);
        verify(authorityService, times(1)).deleterRole(1L, lang);
  }

}