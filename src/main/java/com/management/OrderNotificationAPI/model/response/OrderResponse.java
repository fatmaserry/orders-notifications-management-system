package com.management.OrderNotificationAPI.model.response;

import com.management.OrderNotificationAPI.model.Account;
import com.management.OrderNotificationAPI.model.Order;
import com.management.OrderNotificationAPI.model.*;

public class OrderResponse extends Response {
    private Order order;
    public Order getOrder() {
        return order;
    }
    public void setOrder(Order order) {
        this.order = order;
    }
}
