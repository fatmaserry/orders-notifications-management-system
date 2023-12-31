package com.management.OrderNotificationAPI.model;

import java.util.ArrayList;

public class SimpleOrder extends Order{
    private String username;
    private ArrayList<Product> products;
    private State state;
    private String location;
    private double shippingFees;

    public SimpleOrder(ArrayList<Product> products, String username, int ID, String location){
        super(ID, OrderType.SIMPLE);
        this.products = products;
        this.username = username;
        this.location = location;
        this.shippingFees = 0;
    }

    public double calculateTotalProductCost() {
        double cost = 0;
        for (Product p: this.products) {
            cost += p.getPrice();
        }
        super.setTotalProductCost(cost);
        return cost;
    }

    public ArrayList<SimpleOrder> bringSimpleOrders(){
        ArrayList<SimpleOrder> simpleOrder = new ArrayList<>();
        simpleOrder.add(this);
        return simpleOrder;
    }

    public double calculateTotalShipping() {
        return 100;
    }

    public int calculateNumberOfOrder() {
        return 1;
    }

    public void putShippingFees(double fees) {
        this.shippingFees = fees;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setProducts(ArrayList<Product> products) {
        this.products = products;
    }

    public void setState(State state) {
        this.state = state;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public ArrayList<Product> getProducts() {
        return products;
    }

    public State getState() {
        return state;
    }

    public String getLocation() {
        return location;
    }

    public double getShippingFees() {
        return shippingFees;
    }

    public String getUsername() {
        return username;
    }

}
