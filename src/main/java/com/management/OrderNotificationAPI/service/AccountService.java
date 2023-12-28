package com.management.OrderNotificationAPI.service;

import com.management.OrderNotificationAPI.DB;
import com.management.OrderNotificationAPI.model.Account;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class AccountService implements IAccountService{
    @Override
    public Boolean addAccount(Account p) {
        try {
            if(DB.accounts.get(p.getUsername()) != null){
                return false;
            }
            DB.accounts.put(p.getUsername(), p);
        } catch (Exception e) {
            System.out.println("Exception in addPerson as" + e.getMessage());
            return false;
        }
        return true;
    }

    @Override
    public Account getAccount(String username) {
        try {
            return DB.accounts.get(username);
        } catch (Exception e) {
            System.out.println("Exception in getPerson as" + e.getMessage());
        }
        return null;
    }

    @Override
    public Account[] getAllAccounts() {
        try {
            Set<String> ids = DB.accounts.keySet();
            Account[] p = new Account[ids.size()];
            int i=0;
            for(String id : ids){
                p[i] = DB.accounts.get(id);
                i++;
            }
            return p;
        } catch (Exception e) {
            System.out.println("Exception in getAllPersons as" + e.getMessage());
        }
        return null;
    }
}
