package com.example.clothesshop.model;

import net.sourceforge.jtds.jdbc.DateTime;

import java.io.Serializable;
import java.sql.Date;

public class Purchased implements Serializable {
    private int id;
    private Date createDay;
    private String image;
    private String name;
    private int countItem;
    private int countProduct;
    private int price;
    private int shipCost;
    private int total;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getCreateDay() {
        return createDay;
    }

    public void setCreateDay(Date createDay) {
        this.createDay = createDay;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCountItem() {
        return countItem;
    }

    public void setCountItem(int countItem) {
        this.countItem = countItem;
    }

    public int getCountProduct() {
        return countProduct;
    }

    public void setCountProduct(int countProduct) {
        this.countProduct = countProduct;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getShipCost() {
        return shipCost;
    }

    public void setShipCost(int shipCost) {
        this.shipCost = shipCost;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public Purchased(int id, Date createDay, String image, String name, int countItem, int countProduct, int price, int shipCost, int total) {
        this.id = id;
        this.createDay = createDay;
        this.image = image;
        this.name = name;
        this.countItem = countItem;
        this.countProduct = countProduct;
        this.price = price;
        this.shipCost = shipCost;
        this.total = total;
    }

    public Purchased() {
    }
}
