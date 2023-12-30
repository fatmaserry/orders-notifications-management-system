package com.management.OrderNotificationAPI;
import com.management.OrderNotificationAPI.model.Account;
import com.management.OrderNotificationAPI.model.Order;
import com.management.OrderNotificationAPI.model.Product;
import java.util.HashMap;
import java.util.Map;

public class InMemoryDB extends DB{
    public static Map<String, Account> accounts = new HashMap<String,Account>();
    public static Map<Integer, Product> products = new HashMap<Integer,Product>();
    public static Map<Integer, Order> orders = new HashMap<Integer, Order>();


}
