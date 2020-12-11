package com.yp.builder;

import com.yp.dto.MediaDto;
import com.yp.dto.WaiterDto;
import com.yp.entity.Media;
import com.yp.entity.Waiter;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class WaiterDtoBuilder extends Builder{


    private Long _id;
    private String _name;
    private String _surname;
    private String _dateOfBirth;
    private String _phone;
    private String _mail;
    private MediaDto _media;

    public WaiterDtoBuilder() {
        this._id = 1L;
        this._name ="";
        this._surname = "";
        this._dateOfBirth = "2020-12-10";
        this._phone = "";
        this._mail = "asd@gmail.com";
        this._media = new MediaDtoBuilder().build();
    }

    public WaiterDtoBuilder withId(Long id) {
        this._id= id;
        return this;
    }

    @Override
    public WaiterDtoBuilder withId(int id) {
        return null;
    }

    @Override
    public WaiterDtoBuilder withName(String name) {
        this._name=name;
        return this;
    }

    public WaiterDtoBuilder withSurname(String surname){
        this._surname=surname;
        return this;
    }

    public WaiterDtoBuilder withDateOfBirth(String dateOfBirth){
        this._dateOfBirth = dateOfBirth;
        return this;
    }
    public WaiterDtoBuilder withPhone(String phone){
        this._phone=phone;
        return this;
    }
    public WaiterDtoBuilder withMail(String mail){
        this._mail = mail;
        return this;
    }

    public WaiterDtoBuilder withMedia(MediaDto media){
        this._media = media;
        return this;
    }


    public WaiterDto build(){
        WaiterDto waiter = new WaiterDto();
        waiter.setId(this._id);
        waiter.setName(this._name);
        waiter.setSurname(this._surname);
        waiter.setDateOfBirth(this._dateOfBirth);
        waiter.setPhone(this._phone);
        waiter.setMail(this._mail);
        waiter.setMedia(this._media);
        return waiter;
    }

}
