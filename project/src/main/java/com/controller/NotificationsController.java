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
    
    @GetMapping("/queue")
    public PriorityQueue<Order> getQueue() {
        return notificationsService.getOrdersQueue();
    }
}
