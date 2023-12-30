package com.controller;
import java.util.PriorityQueue;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.model.Order;
import com.service.INotificationsService;

@RestController
@RequestMapping("/api/notifications")
public class NotificationsController {
    @Autowired
    private INotificationsService notificationsService;

    @GetMapping("")
    public String home() {
        // Pritn the instrctions for the user that can be done in this mapping
        return "Welcome to the Notifications API!" +

                "\n\nYou can use the following mappings:" +
                "\n- GET /api/notifications/queue - Get the orders queue";
    }
    
    @GetMapping("/queue")
    public PriorityQueue<Order> getQueue() {
        return notificationsService.getOrdersQueue();
    }
}
