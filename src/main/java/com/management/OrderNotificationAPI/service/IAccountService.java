package com.management.OrderNotificationAPI.service;

import com.management.OrderNotificationAPI.DB;
import com.management.OrderNotificationAPI.model.Account;

import java.util.Set;

public interface IAccountService {
    public Boolean addAccount(Account p);
    public Account getAccount(String username);
    public Account[] getAllAccounts();
}
