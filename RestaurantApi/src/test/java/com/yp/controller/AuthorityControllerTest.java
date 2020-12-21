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
        authorityController.getRole(1);
        verify(authorityService,times(1)).getRole(1);
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
        authorityController.editRole(authorityDto, 1);
        verify(authorityService, times(1)).editRole(authorityDto,1);

    }

    @Test
    public void shouldDeleteWithId() {
        authorityController.deleteRole(1);
        verify(authorityService, times(1)).deleterRole(1);
  }

}