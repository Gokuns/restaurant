package com.yp.builder;

import com.yp.dto.MediaDto;
import com.yp.entity.Customer;
import com.yp.entity.Media;

public class CustomerBuilder extends Builder{

    private Long _id;
    private String _name;
    private String _surname;
    private String _phone;
    private String _address;
    private Media _media;

    public CustomerBuilder(){
        this._id = 1L;
        this._name = "";
        this._surname="";
        this._phone="";
        this._address="";
        this._media= new MediaBuilder().build();
    }


    @Override
    public CustomerBuilder withId(Long id) {
        this._id=id;
        return this;
    }

    @Override
    public CustomerBuilder withName(String name) {
        this._name=name;
        return this;
    }
    public CustomerBuilder withSurname(String surname){
        this._surname=surname;
        return this;
    }
    public CustomerBuilder withPhone(String phone){
        this._phone = phone;
        return this;
    }
    public CustomerBuilder withAddress(String address){
        this._address = address;
        return this;
    }
    public CustomerBuilder withMedia(Media media){
        this._media = media;
        return this;
    }

    public Customer build(){
        Customer customer = new Customer();
        customer.setName(this._name);
        customer.setSurname(this._surname);
        customer.setPhone(this._phone);
        customer.setAddress(this._address);
        customer.setMedia(this._media);
        return customer;
    }
}
