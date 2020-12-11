package com.yp.builder;

import com.yp.entity.Media;
import com.yp.entity.Waiter;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class WaiterBuilder extends Builder{
    private static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    private Long _id;
    private String _name;
    private String _surname;
    private LocalDate _dateOfBirth;
    private String _phone;
    private String _mail;
    private Media _media;

    public WaiterBuilder() {
        this._id = 1L;
        this._name ="";
        this._surname = "";
        this._dateOfBirth = LocalDate.now();
        this._phone = "";
        this._mail = "asd@gmail.com";
        this._media = new MediaBuilder().build();
    }

    public WaiterBuilder withId(Long id) {
        this._id= id;
        return this;
    }

    @Override
    public WaiterBuilder withId(int id) {
        return null;
    }

    @Override
    public WaiterBuilder withName(String name) {
        this._name=name;
        return this;
    }

    public WaiterBuilder withSurname(String surname){
        this._surname=surname;
        return this;
    }

    public WaiterBuilder withDateOfBirth(LocalDate dateOfBirth){
        this._dateOfBirth = dateOfBirth;
        return this;
    }
    public WaiterBuilder withPhone(String phone){
        this._phone=phone;
        return this;
    }
    public WaiterBuilder withMail(String mail){
        this._mail = mail;
        return this;
    }

    public WaiterBuilder withMedia(Media media){
        this._media = media;
        return this;
    }

    public Waiter build(){
        Waiter waiter = new Waiter();
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
