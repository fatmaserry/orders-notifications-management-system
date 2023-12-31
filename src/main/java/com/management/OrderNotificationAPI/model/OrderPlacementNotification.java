package com.management.OrderNotificationAPI.model;

import java.time.LocalDateTime;
import java.util.List;

public class OrderPlacementNotification extends Notification{

    public OrderPlacementNotification(){}

    public OrderPlacementNotification(Language language, Template template, UserInfo receiver, List<Product> products) {
        super(language,template,receiver);
        super.setContent(generateTemplate(receiver, products));

    }
    public String generateTemplate(UserInfo receiver, List<Product> products){
        String template = "Dear " + receiver.getName() + ", your booking of ";
        for(Product p: products) {
            template += p.getName();
            template += ", ";
        }
        template += "is confirmed. thanks for using our store :)";
        return template;
    }

}
