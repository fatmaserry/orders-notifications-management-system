package com.management.OrderNotificationAPI.model.response;

import com.management.OrderNotificationAPI.model.Product;

public class ProductResponse extends Response{
    private Product product;

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
}
