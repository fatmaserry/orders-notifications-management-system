package com.management.OrderNotificationAPI.model;

import java.util.ArrayList;

public class UserInfo {
    private String name, email, phoneNumber;
    private Language language;
    private ArrayList<Channel> channels;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Language getLanguage() {
        return language;
    }

    public void setLanguage(Language language) {
        this.language = language;
    }

    public ArrayList<Channel> getChannels() {
        return channels;
    }

    public void setChannels(ArrayList<Channel> channels) {
        this.channels = channels;
    }
}
