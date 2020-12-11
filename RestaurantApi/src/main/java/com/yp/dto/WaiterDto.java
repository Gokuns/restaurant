package com.yp.dto;

import javax.persistence.Column;
import java.time.LocalDate;

public class WaiterDto {
    private Long id;
    private String name;
    private String surname;
    @Column(name = "DATE_OF_BIRTH")
    private String dateOfBirth;
    private String phone;
    private String mail;
    private MediaDto media;


    public WaiterDto() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public MediaDto getMedia() {
        return media;
    }

    public void setMedia(MediaDto media) {
        this.media = media;
    }
}
