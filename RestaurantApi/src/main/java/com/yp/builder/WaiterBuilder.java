package com.yp.builder;

import com.yp.entity.Media;
import com.yp.entity.Waiter;
import java.time.LocalDate;

public class WaiterBuilder implements Builder{

    private Long id;
    private Long waiterId;
    private String name;
    private String surname;
    private LocalDate dateOfBirth;
    private String phone;
    private String mail;
    private Media media;

    public WaiterBuilder() {
        this.id = 1L;
        this.waiterId=1L;
        this.name ="";
        this.surname = "";
        this.dateOfBirth = LocalDate.now();
        this.phone = "";
        this.mail = "asd@gmail.com";
        this.media = new MediaBuilder().build();
    }
    @Override
    public WaiterBuilder withId(Long id) {
        this.id= id;
        return this;
    }

    public WaiterBuilder withWaiterId(Long id) {
        this.waiterId=id;
        return this;
    }

    @Override
    public WaiterBuilder withName(String name) {
        this.name=name;
        return this;
    }

    public WaiterBuilder withSurname(String surname){
        this.surname=surname;
        return this;
    }

    public WaiterBuilder withDateOfBirth(LocalDate dateOfBirth){
        this.dateOfBirth = dateOfBirth;
        return this;
    }
    public WaiterBuilder withPhone(String phone){
        this.phone=phone;
        return this;
    }
    public WaiterBuilder withMail(String mail){
        this.mail = mail;
        return this;
    }

    public WaiterBuilder withMedia(Media media){
        this.media = media;
        return this;
    }

    public Waiter build(){
        Waiter waiter = new Waiter();
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
