package com.management.OrderNotificationAPI.service;

import com.management.OrderNotificationAPI.model.Notification;

public interface Notifier {
    public boolean send(Notification notification);
}
