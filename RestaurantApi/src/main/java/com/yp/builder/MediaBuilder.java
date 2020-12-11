package com.yp.builder;

import com.yp.entity.Media;

import static org.apache.tomcat.util.buf.HexUtils.fromHexString;

public class MediaBuilder extends Builder {

    private int _id;
    private String _name;
    private byte[] _content;

    public MediaBuilder(){
        this._id=1;
        this._name="";
        this._content= fromHexString("00");
    }

    @Override
    public MediaBuilder withId(int id) {
        this._id = id;
        return this;
    }

    @Override
    public MediaBuilder withName(String name) {
        this._name=name;
        return this;
    }

    public MediaBuilder withContent(byte[] content){
        this._content=content;
        return this;
    }

    public Media build(){
        Media media = new Media();
        media.setName(this._name);
        media.setId(this._id);
        media.setFileContent(this._content);
        return media;
    }
}
