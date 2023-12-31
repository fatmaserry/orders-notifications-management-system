package com.management.OrderNotificationAPI.model;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import lombok.Getter;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;

@Getter
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
    private final OrderType orderType;

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
        return null;
    }
    public ArrayList<Product> getProducts(){
        return null;
    }


    public void setTotalProductCost(double totalProductCost) {
             this.totalProductCost = totalProductCost;
    }

    public void setDate(LocalDateTime createdDate) {
            this.createdDate = createdDate;
    }

    public void setID(int ID) {
            this.ID = ID;
    }

}
