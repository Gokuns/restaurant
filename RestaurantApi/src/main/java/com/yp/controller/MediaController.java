package com.yp.controller;

import com.yp.dto.MediaDto;
import com.yp.service.MediaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/media")
public class MediaController {

    @Autowired
    private MediaService mediaService;

    @GetMapping("/list")
    public List<MediaDto> getAllMedia(){
        return mediaService.getAllMedia();
    }
    @PostMapping("/add")
    public void addMedia(@RequestParam("file")MultipartFile file,@RequestParam String imageName)throws IOException{
        mediaService.addMedia(file,imageName);
    }
}
