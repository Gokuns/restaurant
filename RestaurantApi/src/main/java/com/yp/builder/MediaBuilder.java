package com.yp.builder;

import com.yp.entity.Media;

import static org.apache.tomcat.util.buf.HexUtils.fromHexString;

public class MediaBuilder implements Builder {

    private Long id;
    private String name;
    private byte[] content;

    public MediaBuilder(){
        this.id=1L;
        this.name="";
        this.content= fromHexString("00");
    }

    @Override
    public MediaBuilder withId(Long id) {
        this.id = id;
        return this;
    }

    @Override
    public MediaBuilder withName(String name) {
        this.name=name;
        return this;
    }

    public MediaBuilder withContent(byte[] content){
        this.content=content;
        return this;
    }

    public Media build(){
        Media media = new Media();
        media.setName(this.name);
        media.setId(this.id);
        media.setFileContent(this.content);
        return media;
    }
}
