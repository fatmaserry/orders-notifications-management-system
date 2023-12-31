package com.management.OrderNotificationAPI.service;

import com.management.OrderNotificationAPI.InMemoryDB;
import com.management.OrderNotificationAPI.model.*;
import com.management.OrderNotificationAPI.model.SimpleOrder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class OrderService {
    public Boolean placeOrder(Order order) {
        try {
            if(isExist(order.getID())){
                return false;
            }
            InMemoryDB.orders.put(order.getID(), order);
        }
        catch (Exception e) {
            System.out.println("Exception in placeOrder as " + e.getMessage());
            return false;
        }
        return true;
    }

    public boolean cancelPlacement(int ID) {
        try{
            if(isExist(ID)) {
                InMemoryDB.orders.remove(ID);
                return true;
            }
        } catch (Exception e) {
            System.out.println("Exception in cancelPlacement as " + e.getMessage());
        }
        return false;
    }

    public Order getOrder(int ID) {
        try {
            if(isExist(ID))
                return InMemoryDB.orders.get(ID);
        } catch (Exception e) {
            System.out.println("Exception in get order "+ ID +" as" + e.getMessage());
        }
        return null;
    }

    public boolean isExist(int ID){
        if(InMemoryDB.orders.get(ID) == null){
            return false;
        }
        return true;
    }

    public int generateID(){
        return InMemoryDB.orders.size() + 1;
    }


}
