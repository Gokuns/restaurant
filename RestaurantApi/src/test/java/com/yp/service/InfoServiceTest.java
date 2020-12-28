package com.yp.service;

import com.yp.dto.Info;
import junit.framework.TestCase;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class InfoServiceTest extends TestCase {

    @InjectMocks
    private InfoService infoService;

    @Mock
    private Info info;

    private String lang = "en";


    @Before
    public void setUp(){


    }


    @Test
    public void shouldget(){
        Info i = infoService.getInfo();
        assertNotNull(i);

    }

}