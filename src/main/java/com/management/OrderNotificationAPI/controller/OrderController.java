package com.management.OrderNotificationAPI.controller;

import com.management.OrderNotificationAPI.InMemoryDB;
import com.management.OrderNotificationAPI.model.*;
import com.management.OrderNotificationAPI.model.request.OrderRequest;
import com.management.OrderNotificationAPI.model.response.OrderResponse;
import com.management.OrderNotificationAPI.model.response.Response;
import com.management.OrderNotificationAPI.service.AccountService;
import com.management.OrderNotificationAPI.service.NotificationService;
import com.management.OrderNotificationAPI.service.OrderService;
import com.management.OrderNotificationAPI.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;

@RestController
@RequestMapping("/api/order")
public class OrderController {

    @Autowired
    ProductService productService = new ProductService();

    @Autowired
    OrderService orderService = new OrderService();

    @Autowired
    AccountService accountService = new AccountService();

    @Autowired
    NotificationService notificationService = new NotificationService();

    @GetMapping("/check/simple")
    public OrderResponse checkSimpleOrder(@RequestBody OrderRequest orderRequest){
        OrderResponse response = new OrderResponse();
        if(accountService.getAccount(orderRequest.getUsername()) == null){
            response.setStatus(false);
            response.setMessage("Account doesn't exist");
            return response;
        }

        double amount = 0;
        for (Integer ID: orderRequest.getProducts()) {
            Product p = productService.get(ID);
            if (p == null){
                response.setStatus(false);
                response.setMessage("Product " + ID + " doesn't exist");
                return response;
            }
            else{
                amount += p.getPrice();
            }
        }

        if(!accountService.checkBalance(orderRequest.getUsername(), amount)){
            response.setStatus(false);
            response.setMessage("Account balance is insufficient");
            return response;
        }

        response.setStatus(true);
        return response;
    }

    @PostMapping("/place/simple")
    public OrderResponse placeSimpleOrder(@RequestBody OrderRequest orderRequest) {

        OrderResponse r = checkSimpleOrder(orderRequest);
        if (!r.getStatus()) {
            return r;
        }

        OrderResponse response = new OrderResponse();
        ArrayList<Product> products = new ArrayList<>();
        for (Integer ID : orderRequest.getProducts()) {
            products.add(productService.get(ID));
        }

        SimpleOrder newOrder = new SimpleOrder(products,
                accountService.getAccount(orderRequest.getUsername()),
                orderService.generateID(),
                orderRequest.getLocation());

        newOrder.setState(State.Placed);
        orderService.placeOrder(newOrder);

        // removed items from database
        for (Integer ID : orderRequest.getProducts()) {
            productService.deleteProduct(ID);
        }

        // deducted total cost of order from account
        double amount = newOrder.calculateTotalProductCost();
        accountService.deduce(orderRequest.getUsername(), amount);

        Notification orderPlacementNotification = new OrderPlacementNotification(newOrder.getAccount().getLanguage(), Template.OrderPlacement, newOrder.getAccount(), newOrder.getProducts());
        notificationService.send(orderPlacementNotification);

        response.setStatus(true);
        response.setMessage("Order is placed successfully");
        response.setOrder(newOrder);

        return response;
    }

    @PostMapping("/place/compound")
    public OrderResponse placeCompoundOrder(@RequestBody OrderRequest orderRequest) {

        OrderResponse r = checkSimpleOrder(orderRequest);
        if(!r.getStatus()){
            return r;
        }

        Order simpleOrder = placeSimpleOrder(orderRequest).getOrder();
        Account user = accountService.getAccount(orderRequest.getUsername());

        OrderResponse response = new OrderResponse();
        Order compoundOrder = new CompoundOrder(orderService.generateID());
        compoundOrder.addOrder(simpleOrder);

        for(int ID: orderRequest.getOrders()){
            if(!orderService.isExist(ID)){
                response.setStatus(false);
                response.setMessage("Order " + ID + " isn't placed.");
                return response;
            }
            compoundOrder.addOrder(orderService.getOrder(ID));
        }
        compoundOrder.calculateTotalProductCost();

        double shippingFees = compoundOrder.calculateTotalShipping() / compoundOrder.calculateNumberOfOrder();
        compoundOrder.setShippingFees(shippingFees);


        Notification orderPlacementNotification = new OrderPlacementNotification(user.getLanguage(), Template.OrderPlacement, user, simpleOrder.getProducts());
        notificationService.send(orderPlacementNotification);

        response.setStatus(true);
        response.setMessage("Order is placed successfully");
        response.setOrder(compoundOrder);

        return response;
    }

    @DeleteMapping("/delete/{id}")
    public Response cancelPlacement(@PathVariable("id") int ID) {
        Response response = new Response();

        if(!orderService.isExist(ID)){
            response.setMessage("Order doesn't exist");
            response.setStatus(false);
            return response;
        }

        LocalDateTime now = LocalDateTime.now();
        double timePassed = ChronoUnit.MILLIS.between(now, orderService.getOrder(ID).getCreatedDate());
        double canCancelTime = 1000 * 60 * 60 * 24; //a day
        if(timePassed > canCancelTime){
            response.setMessage("Can't cancel the order as it has been placed since more than a day");
            response.setStatus(false);
            return response;
        }

        ArrayList<SimpleOrder> simpleOrders = orderService.getOrder(ID).getSimpleOrders();

        for (SimpleOrder simpleOrder: simpleOrders){
            for (Product product: simpleOrder.getProducts()){
                productService.addProduct(product);
            }
            accountService.add(simpleOrder.getAccount().getUsername(), simpleOrder.calculateTotalProductCost());
            orderService.cancelPlacement(simpleOrder.getID());
        }
        orderService.cancelPlacement(ID);

        response.setMessage("Order " + ID + " canceled successfully");
        response.setStatus(true);

        return response;
    }


    @GetMapping("/shipment/{id}")
    public Response shipOrder(@PathVariable("id") int ID){
        Response response = new Response();
        if(!orderService.isExist(ID)){
            response.setStatus(false);
            response.setMessage("Order is not placed");
            return response;
        }

        Order order = orderService.getOrder(ID);
        ArrayList<SimpleOrder> simpleOrders = order.getSimpleOrders();
        for (SimpleOrder simpleOrder: simpleOrders) {
            if(!simpleOrder.getState().equals(State.Placed)){
                response.setStatus(false);
                response.setMessage("Your Order has been shipped or delivered already");
                return response;
            }

            if(!accountService.checkBalance(simpleOrder.getAccount().getUsername(), simpleOrder.calculateTotalShipping())){
                response.setStatus(false);
                response.setMessage("Account with username " + simpleOrder.getAccount().getUsername() + " doesn't have enough shipping money");
                return response;
            }

        }

        for (SimpleOrder simpleOrder: simpleOrders) {
            simpleOrder.setState(State.Shipped);
            accountService.deduce(simpleOrder.getAccount().getUsername(), simpleOrder.calculateTotalShipping());
            Notification orderShipmentNotification = new OrderShippmentNotification(simpleOrder.getAccount().getLanguage(),
                    Template.OrderShipment, simpleOrder.getAccount(),
                    simpleOrder.calculateTotalProductCost() + simpleOrder.calculateTotalShipping(),
                    simpleOrder.getID());
            notificationService.send(orderShipmentNotification);
        }

        response.setStatus(true);
        response.setMessage("Order shipped successfully");
        return response;
    }


    @GetMapping("/cancelShipment/{ID}")
    public Response cancelShipment(@PathVariable int ID){
        Response response = new Response();
        if(!orderService.isExist(ID)){
            response.setStatus(false);
            response.setMessage("Order is not placed");
            return response;
        }

        ArrayList<SimpleOrder> simpleOrders = orderService.getOrder(ID).getSimpleOrders();

        for (SimpleOrder simpleOrder: simpleOrders) {
            if(simpleOrder.getState() != State.Shipped){
                response.setStatus(false);
                response.setMessage("Order " + ID + " is not shipped");
                return response;
            }

            LocalDateTime now = LocalDateTime.now();
            double timePassed = ChronoUnit.MILLIS.between(now, simpleOrder.getCreatedDate());
            double canCancelTime = 1000 * 60 * 60 * 24; //a day
            if(timePassed > canCancelTime){
                response.setMessage("Can't cancel the order " + ID + " as it has been shipped since more than a day");
                response.setStatus(false);
                return response;
            }
        }

        for (SimpleOrder simpleOrder: simpleOrders){
            Account account = simpleOrder.getAccount();
            simpleOrder.setState(State.Placed);
            accountService.add(account.getUsername(), account.getBalance()+ simpleOrder.calculateTotalShipping());
        }

        response.setStatus(true);
        response.setMessage("Order " + ID + " shipment is cancelled successfully");
        return response;
    }

    @GetMapping("/get/{ID}")
    public OrderResponse getOrder(@PathVariable int ID){
        OrderResponse orderResponse = new OrderResponse();
        Order order = orderService.getOrder(ID);
        if(order == null){
            orderResponse.setStatus(false);
            orderResponse.setMessage("Product doesn't exist");
            return orderResponse;
        }

        orderResponse.setStatus(true);
        orderResponse.setMessage("success");
        orderResponse.setOrder(order);

        return orderResponse;
    }

    @GetMapping("/get")
    public ArrayList<Order> get(){
        try {
            ArrayList<Order> orders = new ArrayList<>();
            orders.addAll(InMemoryDB.orders.values());
            return orders;
        }
        catch (Exception e) {
            System.out.println("Exception in get products as" + e.getMessage());
        }
        return null;
    }

}
