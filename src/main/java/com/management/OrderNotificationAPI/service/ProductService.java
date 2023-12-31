package com.management.OrderNotificationAPI.service;

import com.management.OrderNotificationAPI.InMemoryDB;
import com.management.OrderNotificationAPI.model.Product;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Set;


@Service
public class ProductService {
    public Boolean addProduct(Product p) {
        try {
            if(InMemoryDB.products.get(p.getSerialNumber()) != null){
                return false;
            }
            InMemoryDB.products.put(p.getSerialNumber(), p);
        } catch (Exception e) {
            System.out.println("Exception in addProduct as" + e.getMessage());
            return false;
        }
        return true;
    }

    public boolean deleteProduct(int serialNumber) {
        try {
            if (InMemoryDB.products.get(serialNumber) != null) {
                InMemoryDB.products.remove(serialNumber);
                return true;
            }
        } catch (Exception e) {
            System.out.println("Exception in deleteProduct as" + e.getMessage());
        }
        return false;
    }

    public ArrayList<Product> getProducts() {
        try {
            ArrayList<Product> products = new ArrayList<>();
            products.addAll(InMemoryDB.products.values());
            return products;
        } catch (Exception e) {
            System.out.println("Exception in getProducts as" + e.getMessage());
        }
        return null;

    }
    public Product get(int serialNumber) {
        try {
          return InMemoryDB.products.get(serialNumber);

        } catch (Exception e) {
            System.out.println("Exception in get as" + e.getMessage());
        }
        return null;
    }
}
