package com.management.OrderNotificationAPI.service;

import com.management.OrderNotificationAPI.InMemoryDB;
import com.management.OrderNotificationAPI.model.Notification;
import com.management.OrderNotificationAPI.model.response.Response;

public class ConcreteNotifier implements Notifier{
    @Override
    public boolean send(Notification notification) {
        return true;
    }
}
