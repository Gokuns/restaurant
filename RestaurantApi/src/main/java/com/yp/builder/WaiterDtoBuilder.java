package com.yp.builder;

import com.yp.dto.MediaDto;
import com.yp.dto.WaiterDto;

public class WaiterDtoBuilder implements Builder{


    private Long id;
    private Long waiterId;
    private String name;
    private String surname;
    private String dateOfBirth;
    private String phone;
    private String mail;
    private MediaDto media;

    public WaiterDtoBuilder() {
        this.id = 1L;
        this.waiterId=1L;
        this.name ="";
        this.surname = "";
        this.dateOfBirth = "2020-12-10";
        this.phone = "";
        this.mail = "asd@gmail.com";
        this.media = new MediaDtoBuilder().build();
    }

    public WaiterDtoBuilder withWaiterId(Long id) {
        this.waiterId= id;
        return this;
    }

    @Override
    public WaiterDtoBuilder withId(Long id) {
        this.id=id;
        return this;
    }

    @Override
    public WaiterDtoBuilder withName(String name) {
        this.name=name;
        return this;
    }

    public WaiterDtoBuilder withSurname(String surname){
        this.surname=surname;
        return this;
    }

    public WaiterDtoBuilder withDateOfBirth(String dateOfBirth){
        this.dateOfBirth = dateOfBirth;
        return this;
    }
    public WaiterDtoBuilder withPhone(String phone){
        this.phone=phone;
        return this;
    }
    public WaiterDtoBuilder withMail(String mail){
        this.mail = mail;
        return this;
    }

    public WaiterDtoBuilder withMedia(MediaDto media){
        this.media = media;
        return this;
    }


    public WaiterDto build(){
        WaiterDto waiter = new WaiterDto();
        waiter.setId(this.id);
        waiter.setWaiterId(this.waiterId);
        waiter.setName(this.name);
        waiter.setSurname(this.surname);
        waiter.setDateOfBirth(this.dateOfBirth);
        waiter.setPhone(this.phone);
        waiter.setMail(this.mail);
        waiter.setMedia(this.media);
        return waiter;
    }

}
