package com.example.clothesshop.model;

public class Rating {
    private int id;
    private int idProduct;
    private double point;

    public Rating(int id, int idProduct, double point) {
        this.id = id;
        this.idProduct = idProduct;
        this.point = point;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdProduct() {
        return idProduct;
    }

    public void setIdProduct(int idProduct) {
        this.idProduct = idProduct;
    }

    public double getPoint() {
        return point;
    }

    public void setPoint(double point) {
        this.point = point;
    }

    public Rating() {
    }
}
