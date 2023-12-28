package com.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.service.NotificationTimer;

@RestController
@RequestMapping("/api")
public class MainController {

    @Autowired
    NotificationTimer notificationTimer;

    @GetMapping("")
    public String home() {
        return "Welcome In the Order Notification Service";
    }

}
