package com.management.OrderNotificationAPI.service;
import com.management.OrderNotificationAPI.InMemoryDB;
import com.management.OrderNotificationAPI.model.Account;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class AccountService {
    public Boolean addAccount(Account p) {
        try {
            if(InMemoryDB.accounts.get(p.getUsername()) != null){
                return false;
            }
            InMemoryDB.accounts.put(p.getUsername(), p);
        } catch (Exception e) {
            System.out.println("Exception in addPerson as" + e.getMessage());
            return false;
        }
        return true;
    }

    public Account getAccount(String username) {
        try {
            return InMemoryDB.accounts.get(username);
        } catch (Exception e) {
            System.out.println("Exception in getPerson as" + e.getMessage());
        }
        return null;
    }

    public Account[] getAllAccounts() {
        try {
            Set<String> ids = InMemoryDB.accounts.keySet();
            Account[] p = new Account[ids.size()];
            int i=0;
            for(String id : ids){
                p[i] = InMemoryDB.accounts.get(id);
                i++;
            }
            return p;
        } catch (Exception e) {
            System.out.println("Exception in getAllPersons as" + e.getMessage());
        }
        return null;
    }
}
