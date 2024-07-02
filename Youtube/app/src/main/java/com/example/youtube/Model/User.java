package com.example.youtube.Model;

import java.io.Serializable;
import java.util.Date;

public class User implements Serializable {
    private String id;
    private String name;
    private String email;
    private String phoneNumber;
    private String sex;
    private String urlAvata;
    private Date birthDate;

    public User(String id, String name, String email, String phoneNumber, String sex, String urlAvata, Date birthDate) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.sex = sex;
        this.urlAvata = urlAvata;
        this.birthDate = birthDate;
    }



    public User(String name, String email, String phoneNumber, String sex, String urlAvata, Date birthDate) {
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.sex = sex;
        this.urlAvata = urlAvata;
        this.birthDate = birthDate;
    }

    public User() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getUrlAvata() {
        return urlAvata;
    }

    public void setUrlAvata(String urlAvata) {
        this.urlAvata = urlAvata;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }
}
