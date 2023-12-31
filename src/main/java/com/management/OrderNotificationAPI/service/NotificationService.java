package com.management.OrderNotificationAPI.service;

import com.management.OrderNotificationAPI.InMemoryDB;
import com.management.OrderNotificationAPI.model.Channel;
import com.management.OrderNotificationAPI.model.Notification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Queue;

@Service
public class NotificationService {
    private Notifier notifier;

    public void setNotifier(Notifier notifier) {
        this.notifier = notifier;
    }

    public boolean send(Notification notification) {
        notifier = new ConcreteNotifier();
        for(Channel channel: notification.getReceiver().getChannels()){
            if(channel == Channel.Email ){
                notifier = new Email(notifier);
            }
            else if (channel == Channel.SMS) {
                notifier = new SMS(notifier);
            }
        }
        return notifier.send(notification);
    }

    public boolean addSentNotification(Notification notification) {
        InMemoryDB.sentNotification.add(notification);
        return true;
    }

    public boolean removeFromQueue(Notification notification) {
        if(InMemoryDB.createdNotifications.contains(notification)) {
            InMemoryDB.createdNotifications.remove(notification);
            return true;
        }
        return false;
    }

    public ArrayList<Notification> getFromQueue() {
        return InMemoryDB.createdNotifications;
    }

    public ArrayList<Notification> getSentNotifications() {
        return InMemoryDB.sentNotification;
    }
}
