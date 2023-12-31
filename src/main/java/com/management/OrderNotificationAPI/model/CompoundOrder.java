package com.management.OrderNotificationAPI.model;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;

import java.util.ArrayList;


@Getter
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
            totalShippingCost += order.calculateTotalShipping();
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

    public void setShippingFees(double fees) {
        for(Order order: orders){
            order.setShippingFees(fees);
        }
    }

    public ArrayList<SimpleOrder> getSimpleOrders(){
        ArrayList<SimpleOrder> simpleOrders = new ArrayList<>();
        for (Order order: orders) {
            if (order instanceof SimpleOrder){
                simpleOrders.add((SimpleOrder) order);
                continue;
            }
            ArrayList<SimpleOrder> ret = order.getSimpleOrders();
            simpleOrders.addAll(ret);
        }
        return simpleOrders;
    }

    public void setOrders(ArrayList<Order> orders) {
             this.orders = orders;
    }

}
