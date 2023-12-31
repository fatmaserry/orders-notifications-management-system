package com.management.OrderNotificationAPI.model;

import java.time.LocalDateTime;
import java.util.List;

public class OrderPlacementNotification extends Notification{

    private List<Product>products;

    public OrderPlacementNotification(Language language, Template template, Account receiver, List<Product> products) {
        super(language,template,receiver);
        String content = "Dear " + receiver.getName() + ", your booking of ";
        for(Product p: products)
        {
            content += p.getName();
            content += ", ";
        }
        content += "is confirmed. thanks for using our store :)";
        super.setContent(content);
        this.products = products;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }
}
