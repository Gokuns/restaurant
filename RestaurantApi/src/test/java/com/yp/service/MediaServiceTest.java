package com.yp.service;

import com.yp.builder.MediaBuilder;
import com.yp.builder.MediaDtoBuilder;
import com.yp.dto.MediaDto;
import com.yp.entity.Media;
import com.yp.repos.MediaRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.any;

@RunWith(MockitoJUnitRunner.class)
public class MediaServiceTest {

    @InjectMocks
    @Autowired
    private MediaService mediaService;

    @Mock
    private MediaRepository mediaRepository;


    private String filepath= "C:\\Users\\gokal\\Desktop\\pic.png";
    private Path path = Paths.get(filepath);
    private String name = "db-changelog/pic.png";
    private String originalfileName= "db-changelog/pic.png";
    private String contentType = "image/png";
    private byte[] content = null;
    private MultipartFile file = null;
    private MediaDto mediaDto = new MediaDtoBuilder().build();
    private Media media = new MediaBuilder().build();
    private List<MediaDto> mediaDtos = new ArrayList<>();
    private List<Media> medias = new ArrayList<>();
    private Path p;



    @Before
    public void setUp() throws Exception {
        content = Files.readAllBytes(path);
        file = new MockMultipartFile(name, originalfileName, contentType, content);
        medias.add(media);
        mediaDtos.add(mediaDto);
        p= Paths.get("D:/codes/restauran/RestaurantApi/target");
    }

    @Test
    public void shouldGetlist(){
        when(mediaRepository.findAll()).thenReturn(medias);
        List<MediaDto> lst = mediaService.getAllMedia();
        assertEquals(lst.get(0).getName(), medias.get(0).getName());
    }

    @Test
    public void shouldAdd(){
        when(mediaRepository.save(any())).thenReturn(media);
        Media m = null;
        try {
            m = mediaService.addMedia(file, "name");
        } catch (IOException e) {
            e.printStackTrace();
        }
        assertNotNull(m);
        assertEquals(m.getName(), media.getName());
    }


}