package com.management.OrderNotificationAPI.model.request;

import com.management.OrderNotificationAPI.model.*;

import java.time.LocalDateTime;
import java.util.List;

public class NotificationRequest {
    private Template template;
    private UserInfo receiver;
    private Channel channel;

    public Template getTemplate() {
        return template;
    }
    public void setTemplate(Template subject) {
        this.template = subject;
    }
    public UserInfo getReceiver() {
        return receiver;
    }
    public void setReceiver(UserInfo receiver) {
        this.receiver = receiver;
    }
    public Channel getChannel() {
        return channel;
    }
    public void setChannel(Channel channel) {
        this.channel = channel;
    }
}
