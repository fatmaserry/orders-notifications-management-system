package com.management.OrderNotificationAPI.service;

import com.management.OrderNotificationAPI.model.Notification;

public abstract class SendingDecorator implements Notifier{

    private Notifier wrappee;

    public SendingDecorator(Notifier wrappee) {
        this.wrappee = wrappee;
    }

    public boolean send(Notification notification)
    {
        return wrappee.send(notification);
    }

}
