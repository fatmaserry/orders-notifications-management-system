package com.management.OrderNotificationAPI.model.request;
import com.management.OrderNotificationAPI.model.Product;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class PlacementNotificationRequest extends NotificationRequest{
    ArrayList<Product> products;

    public void setProducts(ArrayList<Product> products){
        this.products = products;
    }
    public ArrayList<Product> getProducts(){return products;}
}
