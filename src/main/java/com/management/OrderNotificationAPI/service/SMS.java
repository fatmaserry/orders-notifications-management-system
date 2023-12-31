package com.management.OrderNotificationAPI.service;

import com.management.OrderNotificationAPI.repo.InMemoryDB;
import com.management.OrderNotificationAPI.model.Channel;
import com.management.OrderNotificationAPI.model.Notification;

public class SMS extends SendingDecorator {

    public SMS(Notifier wrappee) {
        super(wrappee);
    }

    @Override
    public boolean send(Notification notification) {
        super.send(notification);
        Notification sendnotification = new Notification(notification);
        sendnotification.setChannel(Channel.SMS);
        InMemoryDB.createdNotifications.add(sendnotification);
        return true;
    }
}
