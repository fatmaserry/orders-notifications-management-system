package com.management.OrderNotificationAPI.model;

import java.util.ArrayList;


public class CompoundOrder extends Order{
    private ArrayList<Order> orders;

    public CompoundOrder(int ID){
        super(ID,OrderType.COMPOUND);
        this.orders = new ArrayList<>();
    }
    public CompoundOrder(Order order){
        super(order.getID(), OrderType.COMPOUND);
    }

    public void addOrder(Order order) {
        this.orders.add(order);
    }

    public void removeOrder(Order order) {
        this.orders.remove(order);
    }

    public double calculateTotalProductCost() {
        double totalCost = 0;
        for (Order order: orders) {
            totalCost += order.calculateTotalProductCost();
        }
        super.setTotalProductCost(totalCost);
        return totalCost;
    }

    public double calculateTotalShipping() {
        double totalShippingCost = 0;
        for (Order order: orders) {
            totalShippingCost = Math.max(totalShippingCost, order.calculateTotalShipping());
        }
        return totalShippingCost;
    }

    public int calculateNumberOfOrder() {
        int numOfOrders = 0;
        for (Order order: orders) {
            numOfOrders += order.calculateNumberOfOrder();
        }
        return numOfOrders;
    }

    public void putShippingFees(double fees) {
        for(Order order: orders){
            order.putShippingFees(fees);
        }
    }

    public ArrayList<SimpleOrder> bringSimpleOrders(){
        ArrayList<SimpleOrder> simpleOrders = new ArrayList<>();
        for (Order order: orders) {
            ArrayList<SimpleOrder> ret = order.bringSimpleOrders();
            simpleOrders.addAll(ret);
        }
        return simpleOrders;
    }

    public void setOrders(ArrayList<Order> orders) {
             this.orders = orders;
    }

    public ArrayList<Order> getOrders(){return orders;}
}

