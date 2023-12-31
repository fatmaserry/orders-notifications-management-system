package com.management.OrderNotificationAPI.model;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

import java.io.Serializable;
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

public abstract class Order implements Serializable {
    private double totalProductCost;
    private int ID;
    private LocalDateTime createdDate;
    private OrderType orderType;

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

    public double calculateTotalProductCost() {
        return 0;
    }

    public double calculateTotalShipping() {
        return 0;
    }

    public int calculateNumberOfOrder() {
        return 0;
    }

    public void setShippingFees(double fees) {

    }

    public ArrayList<SimpleOrder> getSimpleOrders(){
        ArrayList<SimpleOrder> arr = new ArrayList<>();
        return arr;
    }

    public void setTotalProductCost(double totalProductCost) {
             this.totalProductCost = totalProductCost;
    }

    public double getTotalProductCost() {
        return totalProductCost;
    }

    public void setDate(LocalDateTime createdDate) {
            this.createdDate = createdDate;
    }

    public LocalDateTime getCreatedDate() {
        return createdDate;
    }

    public void setID(int ID) {
            this.ID = ID;
    }

    public int getID() {
        return ID;
    }

    public OrderType getOrderType(){return orderType;}
}
