package com.example.clothesshop.model;

import java.io.Serializable;

public class Account implements Serializable {
    String username;
    String password;
    int type;
    int idCustomer;
    int id;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getIdCustomer() {
        return idCustomer;
    }

    public void setIdCustomer(int idCustomer) {
        this.idCustomer = idCustomer;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Account(String username, String password, int type, int idCustomer, int id) {
        this.username = username;
        this.password = password;
        this.type = type;
        this.idCustomer = idCustomer;
        this.id = id;
    }

    public Account() {
    }
}
