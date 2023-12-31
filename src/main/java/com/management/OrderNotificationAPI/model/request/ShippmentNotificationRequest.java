package com.management.OrderNotificationAPI.model.request;

public class ShippmentNotificationRequest extends NotificationRequest{
    private double totalCost;
    private int orderID;

    public double getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(double totalCost) {
        this.totalCost = totalCost;
    }

    public int getId() {
        return orderID;
    }

    public void setId(int id) {
         this.orderID = id;
    }
}
