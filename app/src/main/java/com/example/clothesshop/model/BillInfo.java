package com.example.clothesshop.model;

public class BillInfo {
    private int id;
    private String nameProduct;
    private String image;
    private int price;
    private int count;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNameProduct() {
        return nameProduct;
    }

    public void setNameProduct(String nameProduct) {
        this.nameProduct = nameProduct;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public BillInfo(int id, String nameProduct, String image, int price, int count) {
        this.id = id;
        this.nameProduct = nameProduct;
        this.image = image;
        this.price = price;
        this.count = count;
    }

    public BillInfo() {
    }
}
