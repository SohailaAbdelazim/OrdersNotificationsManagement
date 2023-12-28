package com.service;

import com.model.CompoundOrder;
import com.model.Order;

import java.util.PriorityQueue;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NotificationsService implements INotificationsService {
    @Autowired
    private IDatabaseService databaseService;

    private CompoundOrdersService compoundOrdersService;
    private SimpleOrdersService simpleOrdersService;

    @Autowired
    public NotificationsService(CompoundOrdersService compoundOrdersService, SimpleOrdersService simpleOrdersService) {
        this.compoundOrdersService = compoundOrdersService;
        this.simpleOrdersService = simpleOrdersService;
    }

    @Override
    public Boolean sendPlacementNotification(Order order) {
        return true;
    }

    @Override
    public Boolean sendShipmentNotification() {
        System.out.println("Sending shipment notifications");
        for (Order order : databaseService.getOrderQueue()) {
            if (order.getArrivedAt().getTime() <= System.currentTimeMillis()) {
                // send notification
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
        System.out.println("Products size: " + orders.length);
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
