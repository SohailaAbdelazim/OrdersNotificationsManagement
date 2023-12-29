package com.service;

import com.model.CompoundOrder;
import com.model.Order;

import java.util.PriorityQueue;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NotificationsService implements INotificationsService {
    private IDatabaseService databaseService;

    private CompoundOrdersService compoundOrdersService;
    private SimpleOrdersService simpleOrdersService;

    private final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

    @Autowired
    public NotificationsService(CompoundOrdersService compoundOrdersService, SimpleOrdersService simpleOrdersService,
            IDatabaseService databaseService) {
        this.compoundOrdersService = compoundOrdersService;
        this.simpleOrdersService = simpleOrdersService;
        this.databaseService = databaseService;
        scheduler.scheduleAtFixedRate(
                () -> sendShipmentNotification(),
                0, 1, TimeUnit.SECONDS);
    }

    @Override
    public Boolean sendPlacementNotification(Order order) {
        return true;
    }

    @Override
    public Boolean sendShipmentNotification() {
        for (Order order : databaseService.getOrderQueue()) {
            if (order.getArrivedAt().getTime() <= System.currentTimeMillis()) {
                IOrdersService ordersService;
                if (order instanceof CompoundOrder) {
                    ordersService = compoundOrdersService;
                } else {
                    ordersService = simpleOrdersService;
                }
                databaseService.removeOrder(order.getOrderId());
                ordersService.payShipment(order);
            }
        }
        return true;
    }

    @Override
    public PriorityQueue<Order> getOrdersQueue() {
        Order orders[] = databaseService.getOrderQueue();
        // specify a comparison for the priority queue
        PriorityQueue<Order> ordersQueue = new PriorityQueue<Order>((Order o1, Order o2) -> {
            if (o1.getArrivedAt().getTime() < o2.getArrivedAt().getTime()) {
                return -1;
            } else if (o1.getArrivedAt().getTime() > o2.getArrivedAt().getTime()) {
                return 1;
            } else {
                return 0;
            }
        });
        for (Order order : orders) {
            ordersQueue.add(order);
        }
        return ordersQueue;
    }
}
