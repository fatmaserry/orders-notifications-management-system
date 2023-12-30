package com.management.OrderNotificationAPI.model;

import java.util.Date;
import java.time.LocalDate;
import java.util.List;

public abstract class Order {
    private double totalProductCost;
    private LocalDate date;
    private int ID;

    public abstract void addOrder();

    public abstract void removeOrder();

    public abstract List<Product> getItem();

    public abstract double calculateTotalProductCost();

    public abstract double calculateTotalShipping();

    public abstract int calculateNumberOfOrder();

    public abstract void setShippingFees(double fees);

    public double getTotalProductCost() {
        return totalProductCost;
    }

    public void setTotalProductCost(double totalProductCost) {
        this.totalProductCost = totalProductCost;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }
}
