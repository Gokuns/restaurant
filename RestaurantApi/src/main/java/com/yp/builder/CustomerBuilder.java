package com.yp.builder;

import com.yp.entity.Customer;
import com.yp.entity.Media;

public class CustomerBuilder implements Builder{

    private Long id;
    private String name;
    private String surname;
    private String phone;
    private String address;
    private Media media;

    public CustomerBuilder(){
        this.id = 1L;
        this.name = "";
        this.surname="";
        this.phone="";
        this.address="";
        this.media= new MediaBuilder().build();
    }


    @Override
    public CustomerBuilder withId(Long id) {
        this.id=id;
        return this;
    }

    @Override
    public CustomerBuilder withName(String name) {
        this.name=name;
        return this;
    }
    public CustomerBuilder withSurname(String surname){
        this.surname=surname;
        return this;
    }
    public CustomerBuilder withPhone(String phone){
        this.phone = phone;
        return this;
    }
    public CustomerBuilder withAddress(String address){
        this.address = address;
        return this;
    }
    public CustomerBuilder withMedia(Media media){
        this.media = media;
        return this;
    }

    public Customer build(){
        Customer customer = new Customer();
        customer.setId(this.id);
        customer.setName(this.name);
        customer.setSurname(this.surname);
        customer.setPhone(this.phone);
        customer.setAddress(this.address);
        customer.setMedia(this.media);
        return customer;
    }
}
