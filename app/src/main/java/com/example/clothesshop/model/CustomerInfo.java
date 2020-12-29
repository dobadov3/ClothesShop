package com.example.clothesshop.model;

import java.io.Serializable;

public class CustomerInfo implements Serializable {
    private int id;
    private String name;
    private String gender;
    private String tel;
    private String email;
    private String avatar;

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public CustomerInfo(int id, String name, String gender, String tel, String email, String address, String avatar) {
        this.id = id;
        this.name = name;
        this.gender = gender;
        this.tel = tel;
        this.email = email;
        this.address = address;
        this.avatar = avatar;
    }

    public CustomerInfo() {
    }

    private String address;
}
