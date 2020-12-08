package com.example.clothesshop.model;

public class Clothes {
    private int id;
    private int idCategory;
    private String name;
    private int price;
    private String image;
    private String description;

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

    public Clothes(int id, int idCategory, String name, int price, String image, String description) {
        this.id = id;
        this.idCategory = idCategory;
        this.name = name;
        this.price = price;
        this.image = image;
        this.description = description;
    }

    public Clothes() {
    }
}
