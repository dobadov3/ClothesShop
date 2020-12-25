package com.example.clothesshop.model;

import java.io.Serializable;

public class Clothes implements Serializable {
    private int id;
    private int idCategory;
    private String name;
    private int price;
    private String image;
    private String image2;
    private String image3;
    private String image4;
    private String description;
    private int priceSale;

    public int getPriceSale() {
        return priceSale;
    }

    public void setPriceSale(int priceSale) {
        this.priceSale = priceSale;
    }

    public String getImage2() {
        return image2;
    }

    public void setImage2(String image2) {
        this.image2 = image2;
    }

    public String getImage3() {
        return image3;
    }

    public void setImage3(String image3) {
        this.image3 = image3;
    }

    public String getImage4() {
        return image4;
    }

    public void setImage4(String image4) {
        this.image4 = image4;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdCategory() {
        return idCategory;
    }

    public void setIdCategory(int idCategory) {
        this.idCategory = idCategory;
    }

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

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Clothes(int id, int idCategory, String name, int price, String image, String description, String image2, String image3, String image4, int priceSale) {
        this.id = id;
        this.idCategory = idCategory;
        this.name = name;
        this.price = price;
        this.image = image;
        this.image2 = image2;
        this.image3 = image3;
        this.image4 = image4;
        this.description = description;
        this.priceSale = priceSale;
    }

    public Clothes() {
    }
}
