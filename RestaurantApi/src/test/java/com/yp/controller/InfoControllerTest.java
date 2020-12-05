package com.yp.controller;

import com.yp.service.InfoService;
import com.yp.service.UserService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.junit.Assert.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class InfoControllerTest {

    @InjectMocks
    private InfoController infoController;

    @Mock
    private InfoService infoService;

    @Test
    public void shouldgetAuthorityWithId() {
        infoController.lstInfo();
        verify(infoService,times(1)).getInfo();
    }


}