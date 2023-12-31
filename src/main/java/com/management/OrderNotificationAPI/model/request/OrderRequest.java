package com.management.OrderNotificationAPI.model.request;

import java.util.ArrayList;

public class OrderRequest {
    private String username, location;
    private ArrayList<Integer> products = new ArrayList<>();
    private ArrayList<Integer> orders = new ArrayList<>();

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public ArrayList<Integer> getProducts() {
        return products;
    }

    public void setProducts(ArrayList<Integer> products) {
        this.products = products;
    }

    public ArrayList<Integer> getOrders() {
        return orders;
    }

    public void setOrders(ArrayList<Integer> orders) {
        this.orders = orders;
    }
}
