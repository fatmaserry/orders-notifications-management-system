package com.management.OrderNotificationAPI.model.response;

import com.management.OrderNotificationAPI.model.Account;

public class AccountResponse extends Response {
    private Account account;
    public Account getAccount() {
        return account;
    }
    public void setAccount(Account a) {
        this.account = a;
    }

}
