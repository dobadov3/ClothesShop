package com.example.clothesshop.model;

public class Clothes {
    private String name;
    private int price;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public Clothes(String name, int price, int image) {
        this.name = name;
        this.price = price;
        this.image = image;
    }

    public Clothes() {
    }

    private int image;
}