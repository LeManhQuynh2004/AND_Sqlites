package com.quynhlm.dev.and_sqlites.Model;

import java.io.Serializable;

public class Product implements Serializable {
    private int product_id;
    private String name;
    private double price;
    private String describe;
    private int quantity;

    public Product() {
    }

    public Product(int product_id, String name, double price, String describe, int quantity) {
        this.product_id = product_id;
        this.name = name;
        this.price = price;
        this.describe = describe;
        this.quantity = quantity;
    }

    public int getProduct_id() {
        return product_id;
    }

    public void setProduct_id(int product_id) {
        this.product_id = product_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
