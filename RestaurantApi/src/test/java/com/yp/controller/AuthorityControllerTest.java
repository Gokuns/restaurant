package com.yp.controller;

import com.yp.dto.AuthorityDto;
import com.yp.service.AuthorityService;
import junit.framework.TestCase;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import static org.mockito.Mockito.*;
@RunWith(MockitoJUnitRunner.class)
public class AuthorityControllerTest extends TestCase {

    @InjectMocks
    private AuthorityController authorityController;

    @Mock
    private AuthorityService authorityService;

    private AuthorityDto authorityDto = new AuthorityDto();

    @Before
    public void setUp() {
        authorityDto.setAuthority("ROLE_ADMIN");

    }

    @Test
    public void shouldgetAuthorityWithId() {
        authorityController.getRole("ROLE_ADMIN");
        verify(authorityService,times(1)).getRole("ROLE_ADMIN");
    }


    @Test
    public void getAuthorityList(){
        authorityController.getAllRoles();
        verify(authorityService,times(1)).getAllRoles();
 }

    @Test
    public void shouldSaveWithDto() {
        authorityController.addRole(authorityDto);
        verify(authorityService, times(1)).addRole(authorityDto);

 }


    @Test
    public void shouldEditWithDto() {
        authorityController.editRole(authorityDto, "Goko");
        verify(authorityService, times(1)).editRole(authorityDto,"Goko");

    }

    @Test
    public void shouldDeleteWithId() {
        authorityController.deleteRole("ROLE_ADMIN");
        verify(authorityService, times(1)).deleterRole("ROLE_ADMIN");
  }

}