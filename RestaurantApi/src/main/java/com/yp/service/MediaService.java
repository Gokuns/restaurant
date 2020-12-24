package com.yp.service;

import com.yp.dto.MediaDto;
import com.yp.entity.Media;
import com.yp.mapper.MediaMapper;
import com.yp.repos.MediaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.List;

@Service
public class MediaService {
    private static final String JPG_EXTENSION = ".jpg";
    private static final String PNG_EXTENSION = ".png";
    private static final String BMP_EXTENSION = ".bmp";
    private static final String PNG_CONTENT = "image/png";
    private static final String BMP_CONTENT = "image/bmp";


    @Value("${file.upload.dir}")
    private String uploadDir;

    @Autowired
    private MediaRepository mediaRepository;

    @Autowired
    private MediaMapper mediaMapper;

    public List<MediaDto> getAllMedia(){
        List<Media> medias = mediaRepository.findAll();
        List<MediaDto> mediaDtos = new ArrayList<>();
        medias.forEach(media -> mediaDtos.add(mediaMapper.toDto(media)));
        return mediaDtos;
    }

    public Media addMedia(MultipartFile file, String imageName) throws IOException{
        Files.createDirectories(Paths.get("D:/codes/restauran/RestaurantApi/target")); //TODO: paths get use power mock
        String filePath = generateFullFilePath(file);
        Path targetLocation = FileSystems.getDefault().getPath(filePath);
        Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);

        byte[] bytes = file.getBytes();

        Media media = new Media();
        media.setFileContent(bytes);
        media.setName(imageName);
        return mediaRepository.save(media);
    }


    private String generateUUID(){
        return String.valueOf(java.util.UUID.randomUUID());
    }

    private String generateFullFilePath(MultipartFile file){
        String extension = JPG_EXTENSION;

        if (BMP_CONTENT.equals(file.getContentType())){
            extension = BMP_EXTENSION;
        }else if (PNG_CONTENT.equals(file.getContentType())){
            extension = PNG_EXTENSION;
        }
        return uploadDir + generateUUID() + extension;

    }

}
