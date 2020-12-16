package com.example.clothesshop.model;

public class BillInfo {
    private int id;
    private int idBill;
    private int idProduct;
    private int mount;
    private int count;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdBill() {
        return idBill;
    }

    public void setIdBill(int idBill) {
        this.idBill = idBill;
    }

    public int getIdProduct() {
        return idProduct;
    }

    public void setIdProduct(int idProduct) {
        this.idProduct = idProduct;
    }

    public int getMount() {
        return mount;
    }

    public void setMount(int mount) {
        this.mount = mount;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public BillInfo() {
    }

    public BillInfo(int id, int idBill, int idProduct, int mount, int count) {
        this.id = id;
        this.idBill = idBill;
        this.idProduct = idProduct;
        this.mount = mount;
        this.count = count;
    }
}
