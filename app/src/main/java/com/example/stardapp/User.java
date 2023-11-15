package com.example.stardapp;

import java.util.Date;

public class User {
    private String name;
    private String password;
    private String gender;
    private Date birth_date;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Date getBirth_date() {
        return birth_date;
    }

    public void setBirth_date(Date birth_date) {
        this.birth_date = birth_date;
    }

    public User(String name, String password, String gender, Date birth_date) {
        this.name = name;
        this.password = password;
        this.gender = gender;
        this.birth_date = birth_date;
    }

    public User() {
    }
}
