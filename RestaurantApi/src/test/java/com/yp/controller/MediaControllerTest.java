package com.yp.controller;

import com.yp.builder.MediaDtoBuilder;
import com.yp.dto.MediaDto;
import com.yp.service.MediaService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

import static org.junit.Assert.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class MediaControllerTest {

    @InjectMocks
    private MediaController mediaController;

    @Mock
    private MediaService mediaService;

    private final  MediaDto mediaDto = new MediaDtoBuilder().build();
    private final  String lang = "en";

    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void shouldGetAll(){
        mediaController.getAllMedia();
        verify(mediaService,times(1)).getAllMedia();
    }

    @Test
    public void shouldAdd() throws IOException {
        mediaController.addMedia(null, "test", lang);
        verify(mediaService,times(1)).addMedia(null, "test",  lang);
    }

    @Test
    public void shouldDelete(){
        mediaController.deleteMedia(1L, lang);
        verify(mediaService,times(1)).deleteMedia(1L, lang);

    }

}