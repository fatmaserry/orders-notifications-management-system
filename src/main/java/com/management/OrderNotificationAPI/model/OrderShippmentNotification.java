package com.management.OrderNotificationAPI.model;

import java.time.LocalDateTime;
import java.util.List;

public class OrderShippmentNotification extends Notification {

    public OrderShippmentNotification(){}

    public OrderShippmentNotification(Language language, Template template, UserInfo receiver, double totalCost, int Id) {
        super( language, template, receiver);
        super.setContent(generateTemplate(receiver, totalCost, Id));
    }
    public String generateTemplate(UserInfo receiver,double totalCost, int Id){
        String template = "Dear " + receiver.getName() + " Your order number : " + Id + " has been Shipped successfully, with a Total Cost of : " + totalCost;
        return template;
    }
}
