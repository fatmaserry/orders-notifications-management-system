package com.management.OrderNotificationAPI.model;
import java.util.ArrayList;

public class SimpleOrder extends Order{
    private Account account;
    private ArrayList<Product> products;
    private State state;
    private String location;
    private double shippingFees;

    public SimpleOrder(ArrayList<Product> products,Account account, int ID, String location){
        super(ID, OrderType.SIMPLE);
        this.products = products;
        this.account = account;
        this.location = location;
        this.shippingFees = 0;
    }

//    public SimpleOrder(Order order){
//        super(order.getID(), order.getOrderType());
////        this.products = order.getProducts();
////        this.account = order.getAccount();
////        this.location = order.getLocation();
////        this.shippingFees = order.getShippingFees();
//    }

    public double calculateTotalProductCost() {
        double cost = 0;
        for (Product p: this.products) {
            cost += p.getPrice();
        }
        super.setTotalProductCost(cost);
        return cost;
    }

    public double calculateTotalShipping() {
        return 100;
    }

    public int calculateNumberOfOrder() {
        return 1;
    }

    public ArrayList<SimpleOrder> getSimpleOrders(){
        ArrayList<SimpleOrder>simpleOrders = new ArrayList<>();
        simpleOrders.add(this);
        return simpleOrders;
    }

    public void setShippingFees(double fees) {
        this.shippingFees = fees;
    }

    public double getShippingFees() {
        return shippingFees;
    }

    public ArrayList<Product> getProducts(){
        return products;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public void setProducts(ArrayList<Product> products) {
        this.products = products;
    }

    public void setState(State state) {
        this.state = state;
    }

    public State getState() {
        return state;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getLocation() {
        return location;
    }

}
