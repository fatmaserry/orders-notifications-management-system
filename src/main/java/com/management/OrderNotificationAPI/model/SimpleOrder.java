package com.management.OrderNotificationAPI.model;
import java.util.ArrayList;
import java.util.List;

public class SimpleOrder extends Order{
    private Account account;
    private ArrayList<Product> products;
    private State state;
    private String location;
    private double shippingFees;

    @Override
    public void addOrder() {

    }
    @Override
    public List<Product> getItem(){
        return products;
    }
    @Override
    public void removeOrder() {

    }

    @Override
    public double calculateTotalProductCost() {
        return 0;
    }

    @Override
    public double calculateTotalShipping() {
        return 0;
    }

    @Override
    public int calculateNumberOfOrder() {
        return 0;
    }

    @Override
    public void setShippingFees(double fees) {

    }
}
