package com.yp.builder;

import com.yp.dto.MediaDto;

import static org.apache.tomcat.util.buf.HexUtils.fromHexString;

public class MediaDtoBuilder implements Builder{
    private Long id;
    private String name;
    private byte[] content;

    public MediaDtoBuilder(){
        this.id=1L;
        this.name="";
        this.content= fromHexString("00");
    }

    @Override
    public MediaDtoBuilder withId(Long id) {
        this.id = id;
        return this;
    }

    @Override
    public MediaDtoBuilder withName(String name) {
        this.name=name;
        return this;
    }

    public MediaDtoBuilder withContent(byte[] content){
        this.content=content;
        return this;
    }

    public MediaDto build(){
        MediaDto media = new MediaDto();
        media.setName(this.name);
        media.setId(this.id);
        media.setFileContent(this.content);
        return media;
    }
}
