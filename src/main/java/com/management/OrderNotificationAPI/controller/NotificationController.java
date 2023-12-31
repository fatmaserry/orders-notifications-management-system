package com.management.OrderNotificationAPI.controller;
import com.management.OrderNotificationAPI.model.*;
import com.management.OrderNotificationAPI.model.request.PlacementNotificationRequest;
import com.management.OrderNotificationAPI.model.request.ShippmentNotificationRequest;
import com.management.OrderNotificationAPI.model.response.Response;
import com.management.OrderNotificationAPI.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.*;

@RestController
@RequestMapping("/api/notification")
public class NotificationController {

    @Autowired
    private NotificationService notificationService = new NotificationService();

    @GetMapping("/generate/placement")
    public Notification generate(@RequestBody PlacementNotificationRequest notificationRequest){
        return new OrderPlacementNotification(notificationRequest.getReceiver().getLanguage(),
                Template.OrderPlacement, notificationRequest.getReceiver(), notificationRequest.getProducts());
    }

    @GetMapping("/generate/shipment")
    public Notification generate(@RequestBody ShippmentNotificationRequest notificationRequest){
        return new OrderShippmentNotification(notificationRequest.getReceiver().getLanguage(),
                Template.OrderShipment,
                notificationRequest.getReceiver(),
                notificationRequest.getTotalCost(),
                notificationRequest.getId());
    }

    @PostMapping("/send")
    public Response send(@RequestBody Notification notification){
        Response response = new Response();

        if(notificationService.send(notification)){
            response.setStatus(true);
            response.setMessage("sent successfully");
        }
        else{
            response.setStatus(false);
            response.setMessage("unable to send");
        }

        return response;
    }

    @Scheduled(fixedRate = 1000 * 60)
    public void schedule() {

        LocalDateTime now = LocalDateTime.now();
        Response response = new Response();
        boolean RemovedFromQueue = false;


        ArrayList<Notification>toBeSent = new ArrayList<>();

        for (Notification notification : notificationService.getFromQueue())
        {
            if(now.minusMinutes(1).isAfter(notification.getCreatedAt())) {
                toBeSent.add(notification);
            }
        }

        for (Notification notification: toBeSent){
            notificationService.removeFromQueue(notification);
            notificationService.addSentNotification(notification);
            RemovedFromQueue = true;
        }
    }

    @GetMapping("/get/mostNotifiedEmail")
    public String getMostNotifiedEmail() {

            HashMap<String, Integer> mp = new HashMap<>();
            for (Notification notification: notificationService.getSentNotifications()){
                if(notification.getChannel() == Channel.Email){
                    mp.put(notification.getReceiver().getEmail(),mp.getOrDefault(notification.getReceiver().getEmail(),0)+1);
                }
            }

            int maxNotification = 0;
            String MostNotifiedEmail = "";
            for (Map.Entry<String, Integer>m: mp.entrySet()){
                if(m.getValue() > maxNotification){
                    maxNotification = m.getValue();
                    MostNotifiedEmail = m.getKey();
                }
            }
            return MostNotifiedEmail;
    }

    @GetMapping("/get/mostNotifiedPhone")
    public String getMostNotifiedPhone() {

        HashMap<String, Integer> mp = new HashMap<>();
        for (Notification notification: notificationService.getSentNotifications()){
            if(notification.getChannel() == Channel.SMS){
                mp.put(notification.getReceiver().getPhoneNumber(),mp.getOrDefault(notification.getReceiver().getPhoneNumber(),0)+1);
            }
        }

        int maxNotification=0;
        String MostNotifiedPhone = "";
        for (Map.Entry<String, Integer>m: mp.entrySet()){
            if(m.getValue() > maxNotification){
                maxNotification = m.getValue();
                MostNotifiedPhone = m.getKey();
            }
        }
        return MostNotifiedPhone;
    }

    @GetMapping("/get/mostSentTemplate")
    public Template getMostSentTemplate() {

            HashMap<Template, Integer> mp = new HashMap<>();
            for (Notification notification : notificationService.getSentNotifications()) {
                mp.put(notification.getTemplate(), mp.getOrDefault(notification.getTemplate(), 0) + 1);
            }

            int maxNotification = 0;
            Template MostSentTemplete = null;

            for (Map.Entry<Template, Integer> entry : mp.entrySet()) {
                if (entry.getValue() > maxNotification) {
                    maxNotification = entry.getValue();
                    MostSentTemplete=entry.getKey();
                }
            }

        return MostSentTemplete;
    }

    @GetMapping("/getFromQueue")
    public ArrayList<Notification> getFromQueue(){
        return notificationService.getFromQueue();
    }

    @GetMapping("/getSent")
    public ArrayList<Notification> getSent(){
        return notificationService.getSentNotifications();
    }

}
