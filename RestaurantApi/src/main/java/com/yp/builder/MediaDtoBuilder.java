package com.yp.builder;

import com.yp.dto.MediaDto;
import com.yp.entity.Media;

import static org.apache.tomcat.util.buf.HexUtils.fromHexString;

public class MediaDtoBuilder extends Builder{
    private int _id;
    private String _name;
    private byte[] _content;

    public MediaDtoBuilder(){
        this._id=1;
        this._name="";
        this._content= fromHexString("00");
    }

    @Override
    public MediaDtoBuilder withId(int id) {
        this._id = id;
        return this;
    }

    @Override
    public MediaDtoBuilder withName(String name) {
        this._name=name;
        return this;
    }

    public MediaDtoBuilder withContent(byte[] content){
        this._content=content;
        return this;
    }

    public MediaDto build(){
        MediaDto media = new MediaDto();
        media.setName(this._name);
        media.setId(this._id);
        media.setFileContent(this._content);
        return media;
    }
}
