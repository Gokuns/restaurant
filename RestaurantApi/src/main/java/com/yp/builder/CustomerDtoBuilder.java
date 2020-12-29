package com.yp.builder;

import com.yp.dto.CustomerDto;
import com.yp.dto.MediaDto;
import com.yp.entity.Customer;
import com.yp.entity.Media;

public class CustomerDtoBuilder extends Builder {
    private Long _id;
    private String _name;
    private String _surname;
    private String _phone;
    private String _address;
    private MediaDto _media;

    public CustomerDtoBuilder(){
        this._id = 1L;
        this._name = "";
        this._surname="";
        this._phone="";
        this._address="";
        this._media= new MediaDtoBuilder().build();
    }


    @Override
    public CustomerDtoBuilder withId(Long id) {
        this._id=id;
        return this;
    }

    @Override
    public CustomerDtoBuilder withName(String name) {
        this._name=name;
        return this;
    }
    public CustomerDtoBuilder withSurname(String surname){
        this._surname=surname;
        return this;
    }
    public CustomerDtoBuilder withPhone(String phone){
        this._phone = phone;
        return this;
    }
    public CustomerDtoBuilder withAddress(String address){
        this._address = address;
        return this;
    }
    public CustomerDtoBuilder withMedia(MediaDto media){
        this._media = media;
        return this;
    }

    public CustomerDto build(){
        CustomerDto customer = new CustomerDto();
        customer.setName(this._name);
        customer.setSurname(this._surname);
        customer.setPhone(this._phone);
        customer.setAddress(this._address);
        customer.setMedia(this._media);
        return customer;
    }
}
