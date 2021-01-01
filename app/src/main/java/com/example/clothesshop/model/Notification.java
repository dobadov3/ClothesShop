package com.example.clothesshop.model;

import java.util.Date;

public class Notification {
    private int id;
    private int idAccount;
    private Date date;
    private String title;
    private String content;
    private String img;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdAccount() {
        return idAccount;
    }

    public void setIdAccount(int idAccount) {
        this.idAccount = idAccount;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public Notification(int id, int idAccount, Date date, String title, String content, String img) {
        this.id = id;
        this.idAccount = idAccount;
        this.date = date;
        this.title = title;
        this.content = content;
        this.img = img;
    }

    public Notification() {
    }
}
