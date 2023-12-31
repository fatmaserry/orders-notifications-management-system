package com.management.OrderNotificationAPI.model;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

import java.time.LocalDateTime;
import java.util.ArrayList;

@JsonTypeInfo(
        include = JsonTypeInfo.As.EXISTING_PROPERTY,
        property = "orderType",
        use = JsonTypeInfo.Id.NAME,
        visible = true
)
@JsonSubTypes({
        @JsonSubTypes.Type(value = SimpleOrder.class, name = "SIMPLE"),
        @JsonSubTypes.Type(value = CompoundOrder.class, name = "COMPOUND")
})

public abstract class Order{
    private double totalProductCost;
    private int ID;
    private LocalDateTime createdDate, shippingDate;
    private final OrderType orderType;

    public double getTotalProductCost() {
        return totalProductCost;
    }

    public int getID() {
        return ID;
    }

    public OrderType getOrderType() {
        return orderType;
    }

    Order(int ID, OrderType orderType){
        this.orderType = orderType;
        this.createdDate = LocalDateTime.now();
        this.ID = ID;
    }

    public void addOrder(Order order){
        throw new UnsupportedOperationException();
    }

    public void removeOrder(Order order){
        throw new UnsupportedOperationException();
    }

    public abstract double calculateTotalProductCost();

    public abstract double calculateTotalShipping();

    public abstract int calculateNumberOfOrder();

    public abstract void putShippingFees(double fees);

    public ArrayList<SimpleOrder> bringSimpleOrders(){
        return null;
    }

    public void setTotalProductCost(double totalProductCost) {
             this.totalProductCost = totalProductCost;
    }

    public void setCreatedDate(LocalDateTime createdDate) {
            this.createdDate = createdDate;
    }

    public LocalDateTime getCreatedDate(){return createdDate;}

    public void setID(int ID) {
            this.ID = ID;
    }

    public LocalDateTime getShippingDate() {
        return shippingDate;
    }

    public void setShippingDate(LocalDateTime shippingDate) {
        this.shippingDate = shippingDate;
    }
}
