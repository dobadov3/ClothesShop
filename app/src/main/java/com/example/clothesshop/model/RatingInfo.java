package com.example.clothesshop.model;

import net.sourceforge.jtds.jdbc.DateTime;

import java.util.Date;

public class RatingInfo {
    private int id;
    private String comment;
    private Date date;
    private float point;

    public String getNameCus() {
        return nameCus;
    }

    public void setNameCus(String nameCus) {
        this.nameCus = nameCus;
    }

    private String nameCus;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public float getPoint() {
        return point;
    }

    public void setPoint(float point) {
        this.point = point;
    }

    public RatingInfo(int id, String comment, Date date, float point, String nameCus) {
        this.id = id;
        this.comment = comment;
        this.date = date;
        this.point = point;
        this.nameCus = nameCus;
    }

    public RatingInfo() {
    }

}
