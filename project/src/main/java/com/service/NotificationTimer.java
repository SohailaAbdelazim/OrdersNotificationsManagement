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

    public NotificationTimer() {
        // scheduler.scheduleAtFixedRate(notificationsService::sendShipmentNotification, 0, 1, TimeUnit.SECONDS);
        // fix it because doesn't loop forever
        scheduler.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                System.out.println("Sending notification");
                notificationsService.sendShipmentNotification();
            }
        }, 0, 1, TimeUnit.SECONDS);
    }
}
