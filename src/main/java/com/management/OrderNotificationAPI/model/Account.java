package com.management.OrderNotificationAPI.model;

public class Account {
    private UserInfo userInfo;
    private String username, password;
    private double balance;

    public Account(UserInfo userInfo, String password, double balance) {
        this.userInfo = userInfo;
        this.password = password;
        this.balance = balance;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public UserInfo getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(UserInfo userInfo) {
        this.userInfo = userInfo;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }
}
