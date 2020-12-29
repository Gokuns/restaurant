package com.yp.service;

import com.yp.dto.MediaDto;
import com.yp.entity.Media;
import com.yp.exception.BusinessRuleException;
import com.yp.exception.ContentNotFoundException;
import com.yp.mapper.MediaMapper;
import com.yp.repos.MediaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.*;
import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class MediaService {
    private static final String JPG_EXTENSION = ".jpg";
    private static final String PNG_EXTENSION = ".png";
//    private static final String BMP_EXTENSION = ".bmp";
    private static final String PNG_CONTENT = "image/png";
//    private static final String BMP_CONTENT = "image/bmp";
    private static final String BUSINESS_RULE_EXCEPTION = "BusinessRuleException";
    private static final String CONTENT_NOT_FOUND = "ContentNotFound";


    @Value("${file.upload.dir}")
    private String uploadDir;

    @Autowired
    private MediaRepository mediaRepository;

    @Autowired
    private MessageSource messageSource;

    @Autowired
    private MediaMapper mediaMapper;

    public List<MediaDto> getAllMedia(){
        return mediaRepository.findAll().stream().map(mediaMapper::toDto).collect(Collectors.toList());
    }

    public Media addMedia(MultipartFile file, String imageName, String lang) throws IOException{
        if(file==null || file.isEmpty()){
            throw new BusinessRuleException(messageSource.getMessage(BUSINESS_RULE_EXCEPTION, new Object[0], new Locale(lang)));
        }
        if(imageName==null || imageName.isEmpty()){
            throw new BusinessRuleException(messageSource.getMessage(BUSINESS_RULE_EXCEPTION, new Object[0], new Locale(lang)));

        }
        Files.createDirectories(Paths.get("D:/codes/restauran/RestaurantApi/target"));
        String filePath = generateFullFilePath(file);
        Path targetLocation = FileSystems.getDefault().getPath(filePath);
        Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);

        byte[] bytes = file.getBytes();

        Media media = new Media();
        media.setFileContent(bytes);
        media.setName(imageName);
        return mediaRepository.save(media);
    }

    public void deleteMedia(Long id, String lang){
        if(id==null){
            throw new BusinessRuleException(messageSource.getMessage(BUSINESS_RULE_EXCEPTION, new Object[0], new Locale(lang)));
        }
        Optional<Media> optionalMedia = mediaRepository.findById(id);
        if(optionalMedia.isEmpty()){
            throw new ContentNotFoundException(messageSource.getMessage(CONTENT_NOT_FOUND, new Object[0], new Locale(lang)));
        }
        mediaRepository.deleteById(id);
    }


    private String generateUUID(){
        return String.valueOf(java.util.UUID.randomUUID());
    }

    private String generateFullFilePath(MultipartFile file){
        String extension = JPG_EXTENSION;

         if (PNG_CONTENT.equals(file.getContentType())){
            extension = PNG_EXTENSION;
        }
        return uploadDir + generateUUID() + extension;

    }

}
