package com.quynhlm.dev.and_sqlites.Model;

public class Orders {
    private int id;
    private String name;
    private double price;
    private int quantity;
    private double money;

    private int product_id;

    public Orders(int id, String name, double price, int quantity, double money, int product_id) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.money = money;
        this.product_id = product_id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getMoney() {
        return money;
    }

    public void setMoney(double money) {
        this.money = money;
    }
}
