package com.management.OrderNotificationAPI.service;

import com.management.OrderNotificationAPI.InMemoryDB;
import com.management.OrderNotificationAPI.model.Channel;
import com.management.OrderNotificationAPI.model.Notification;

public class Email extends SendingDecorator{

    public Email(Notifier wrappee) {
        super(wrappee);
    }

    @Override
    public  boolean send(Notification notification) {
        super.send(notification);
        Notification sendnotification = new Notification(notification);
        sendnotification.setChannel(Channel.Email);
        InMemoryDB.createdNotifications.add(notification);
        return true;
    }
}
