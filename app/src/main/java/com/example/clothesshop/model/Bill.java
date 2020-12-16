package com.example.clothesshop.model;

import net.sourceforge.jtds.jdbc.DateTime;

public class Bill {
    private int id;
    private int idAccount;
    private DateTime createDay;
    private int discount;

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

    public DateTime getCreateDay() {
        return createDay;
    }

    public void setCreateDay(DateTime createDay) {
        this.createDay = createDay;
    }

    public int getDiscount() {
        return discount;
    }

    public void setDiscount(int discount) {
        this.discount = discount;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public Bill(int id, int idAccount, DateTime createDay, int discount, int total) {
        this.id = id;
        this.idAccount = idAccount;
        this.createDay = createDay;
        this.discount = discount;
        this.total = total;
    }

    public Bill() {
    }

    private int total;
}
