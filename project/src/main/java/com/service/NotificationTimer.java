package com.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@Service
public class NotificationTimer {
    private final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
    @Autowired
    private INotificationsService notificationsService;

    public Boolean start(){
        scheduler.scheduleAtFixedRate(notificationsService::sendShipmentNotification, 0, 1, TimeUnit.SECONDS);
        return true;
    }
}
