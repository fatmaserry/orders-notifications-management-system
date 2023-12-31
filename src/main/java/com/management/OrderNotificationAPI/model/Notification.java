package com.management.OrderNotificationAPI.model;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import org.apache.catalina.User;

import java.time.LocalDateTime;

@JsonTypeInfo(
        include = JsonTypeInfo.As.EXISTING_PROPERTY,
        property = "template",
        use = JsonTypeInfo.Id.NAME,
        visible = true
)
@JsonSubTypes({
        @JsonSubTypes.Type(value = OrderPlacementNotification.class, name = "OrderPlacement"),
        @JsonSubTypes.Type(value = OrderShippmentNotification.class, name = "OrderShipment")
})

public class Notification {
    private Language language;
    private Template template;
    private String content;
    private UserInfo receiver;
    private Channel channel;
    private LocalDateTime createdAt;

    public Notification(){}

    public Notification(Language language, Template template, UserInfo receiver) {
        this.language = language;
        this.template = template;
        this.receiver = receiver;
        this.createdAt = LocalDateTime.now();
    }

    public Notification(Notification notification)
    {
        this.language = notification.getLanguage();
        this.content = notification.getContent();
        this.receiver = notification.getReceiver();
        this.template = notification.getTemplate();
        this.createdAt = notification.getCreatedAt();
    }

    public Language getLanguage() {
        return language;
    }

    public void setLanguage(Language language) {
        this.language = language;
    }

    public Template getTemplate() {
        return template;
    }

    public void setTemplate(Template subject) {
        this.template = subject;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
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

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
