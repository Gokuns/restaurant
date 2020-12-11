package com.yp.dto;

public class CategoryDto {
    private int id;
    private String name;
    private MediaDto media;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public MediaDto getMedia() {
        return media;
    }

    public void setMedia(MediaDto media) {
        this.media = media;
    }
}
