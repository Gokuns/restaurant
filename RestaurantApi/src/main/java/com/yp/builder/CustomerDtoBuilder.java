package com.yp.builder;

import com.yp.dto.CustomerDto;
import com.yp.dto.MediaDto;

public class CustomerDtoBuilder implements Builder {
    private Long id;
    private String name;
    private String surname;
    private String phone;
    private String address;
    private MediaDto media;

    public CustomerDtoBuilder(){
        this.id = 1L;
        this.name = "";
        this.surname="";
        this.phone="";
        this.address="";
        this.media= new MediaDtoBuilder().build();
    }


    @Override
    public CustomerDtoBuilder withId(Long id) {
        this.id=id;
        return this;
    }

    @Override
    public CustomerDtoBuilder withName(String name) {
        this.name=name;
        return this;
    }
    public CustomerDtoBuilder withSurname(String surname){
        this.surname=surname;
        return this;
    }
    public CustomerDtoBuilder withPhone(String phone){
        this.phone = phone;
        return this;
    }
    public CustomerDtoBuilder withAddress(String address){
        this.address = address;
        return this;
    }
    public CustomerDtoBuilder withMedia(MediaDto media){
        this.media = media;
        return this;
    }

    public CustomerDto build(){
        CustomerDto customer = new CustomerDto();
        customer.setId(this.id);
        customer.setName(this.name);
        customer.setSurname(this.surname);
        customer.setPhone(this.phone);
        customer.setAddress(this.address);
        customer.setMedia(this.media);
        return customer;
    }
}
