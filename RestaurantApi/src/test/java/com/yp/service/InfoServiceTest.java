package com.yp.service;

import com.yp.dto.Info;
import junit.framework.TestCase;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class InfoServiceTest extends TestCase {

    @InjectMocks
    private InfoService infoService;

    @Mock
    private Info info;


    @Before
    public void setUp(){
        info.setDll_auto("Merhaba");

    }


    @Test
    public void shouldget(){
        Info i = infoService.getInfo();
        assertNotNull(i);

    }

}