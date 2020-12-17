package com.example.clothesshop.model;

import java.io.Serializable;

public class Cart implements Serializable {
    Clothes clothes;
    String count;
    public static int price = 0;

    public Clothes getClothes() {
        return clothes;
    }

    public void setClothes(Clothes clothes) {
        this.clothes = clothes;
    }

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }

    public Cart(Clothes clothes, String count) {
        this.clothes = clothes;
        this.count = count;
    }

    public Cart() {
    }
}
