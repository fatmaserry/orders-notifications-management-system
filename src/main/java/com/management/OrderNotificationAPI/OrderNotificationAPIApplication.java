package com.management.OrderNotificationAPI;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class OrderNotificationAPIApplication {

	public static void main(String[] args) {
		SpringApplication.run(OrderNotificationAPIApplication.class, args);
	}

}
