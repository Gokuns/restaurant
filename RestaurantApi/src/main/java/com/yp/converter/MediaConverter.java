package com.yp.converter;

import com.yp.dto.MediaDto;
import com.yp.entity.Media;

public class MediaConverter {

    public static Media convertToMedia(MediaDto mediaDto){
        Media media = new Media();
        media.setId(mediaDto.getId());
        media.setName(mediaDto.getName());
        media.setFileContent(mediaDto.getFileContent());
        return media;

    }
    public static MediaDto convertToMediaDto(Media media){
        MediaDto mediaDto = new MediaDto();
        mediaDto.setId(media.getId());
        mediaDto.setName(media.getName());
        mediaDto.setFileContent(media.getFileContent());
        return mediaDto;
    }
}
