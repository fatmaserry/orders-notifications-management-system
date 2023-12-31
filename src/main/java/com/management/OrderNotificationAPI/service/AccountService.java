package com.management.OrderNotificationAPI.service;

import com.management.OrderNotificationAPI.InMemoryDB;
import com.management.OrderNotificationAPI.model.Account;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Set;

@Service
public class AccountService {
    public Boolean addAccount(Account p) {
        try {
            if (InMemoryDB.accounts.get(p.getUsername()) != null) {
                return false;
            }
            InMemoryDB.accounts.put(p.getUsername(), p);
        } catch (Exception e) {
            System.out.println("Exception in addAccount as" + e.getMessage());
            return false;
        }
        return true;
    }

    public Account getAccount(String username) {
        try {
            return InMemoryDB.accounts.get(username);
        } catch (Exception e) {
            System.out.println("Exception in getAccount as" + e.getMessage());
        }
        return null;
    }

    public ArrayList<Account> getAllAccounts() {
        try {
            ArrayList<Account> accounts = new ArrayList<>();
            accounts.addAll(InMemoryDB.accounts.values());
            return accounts;
        }
        catch (Exception e) {
            System.out.println("Exception in getAllAccounts as" + e.getMessage());
        }
        return null;
    }

    public void deduce(String username, double amount) {
        Account account = getAccount(username);
        double newBalance = account.getBalance() - amount;
        account.setBalance(newBalance);
    }

    public void add(String username, double amount) {
        Account account = getAccount(username);
        double newBalance = account.getBalance() + amount;
        account.setBalance(newBalance);
    }

    public boolean checkBalance(String username, double amount) {
        Account account = getAccount(username);
        if (account.getBalance() >= amount)
            return true;
        return false;
    }
}
