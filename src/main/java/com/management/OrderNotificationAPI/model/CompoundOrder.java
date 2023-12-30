package com.management.OrderNotificationAPI.model;

import java.util.ArrayList;
import java.util.List;

public class CompoundOrder extends Order{
    private ArrayList<Order> orders;

    public ArrayList<Order> getOrders() {
        return orders;
    }

    public void setOrders(ArrayList<Order> orders) {
        this.orders = orders;
    }

    @Override
    public void addOrder() {

    }

    @Override
    public void removeOrder() {

    }

    @Override
    public List<Product> getItem() {
        return null;
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
