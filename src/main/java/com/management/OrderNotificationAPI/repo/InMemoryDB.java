package com.management.OrderNotificationAPI.repo;
import com.management.OrderNotificationAPI.model.Account;
import com.management.OrderNotificationAPI.model.Order;
import com.management.OrderNotificationAPI.model.Product;
import com.management.OrderNotificationAPI.model.Notification;

import java.util.*;

public class InMemoryDB extends DB{
    public static Map<String, Account> accounts = new HashMap<String,Account>();
    public static Map<Integer, Product> products = new HashMap<Integer,Product>();
    public static Map<Integer, Order> orders = new HashMap<Integer, Order>();
    public static ArrayList<Notification> createdNotifications= new ArrayList<Notification>() ;
    public static ArrayList<Notification> sentNotification  = new ArrayList<>();

    public class sentNotification {
    }
}
