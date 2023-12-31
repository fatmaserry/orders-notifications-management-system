package com.management.OrderNotificationAPI.model;

import java.time.LocalDateTime;
import java.util.List;

public class OrderShippmentNotification extends Notification {

    private int orderID;
    private double totalCost;

    public OrderShippmentNotification(Language language, Template template, Account receiver, double totalCost, int Id) {
        super( language, template, receiver);
        this.totalCost = totalCost;
        this.orderID = Id;
        String content = "Dear " + receiver.getName() + " Your order number : " + orderID + " has been Shipped successfully, with a Total Cost of : " + totalCost;
        super.setContent(content);
    }

    public int getOrderID() {
        return orderID;
    }

    public void setOrderID(int orderID) {
        this.orderID = orderID;
    }

    public double getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(double totalCost) {
        this.totalCost = totalCost;
    }


}
