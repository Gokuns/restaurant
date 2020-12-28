package com.yp.controller;

import com.yp.dto.MediaDto;
import com.yp.service.MediaService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Api(tags = "Media Controller")
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/medias")
public class MediaController {

    @Autowired
    private MediaService mediaService;

    @GetMapping
    @PreAuthorize("hasAuthority('ADMIN') || hasAuthority('USER')")
    public List<MediaDto> getAllMedia(){
        return mediaService.getAllMedia();
    }

    @PostMapping
    @PreAuthorize("hasAuthority('ADMIN')")
    public void addMedia(@RequestParam("file")MultipartFile file,@RequestParam String imageName, @RequestParam(value = "lang", defaultValue = "en")String lang)throws IOException{
        mediaService.addMedia(file,imageName, lang);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public void deleteMedia(@PathVariable(value = "id") Long id, @RequestParam(value = "lang", defaultValue = "en")String lang){
        mediaService.deleteMedia(id, lang);
    }

}
