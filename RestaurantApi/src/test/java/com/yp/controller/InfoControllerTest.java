package com.yp.controller;

import com.yp.dto.Info;
import com.yp.service.InfoService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import junit.framework.TestCase;



import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class InfoControllerTest extends TestCase{

    @InjectMocks
    private InfoController infoController;

    @Mock
    private InfoService infoService;



    private String lang = "en";

    @Test
    public void shouldgetInfoWithId() {
        when(infoService.getInfo()).thenReturn(new Info());
        Info info = infoController.lstInfo();
        assertNotNull(info);
    }


}