package com.management.OrderNotificationAPI.service;

import com.management.OrderNotificationAPI.model.Notification;

public class ConcreteNotifier implements Notifier{
    @Override
    public boolean send(Notification notification) {
        return true;
    }
}
