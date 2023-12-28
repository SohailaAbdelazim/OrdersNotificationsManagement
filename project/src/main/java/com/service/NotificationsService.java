package com.service;

import com.model.Order;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class NotificationsService implements INotificationsService {
    private Map<Integer, Order> orderQueue;

    NotificationsService() {
        this.orderQueue = new HashMap<>();
    }

    @Override
    public Boolean sendPlacementNotification(Order order) {
        return true;
    }

    @Override
    public Order insertNewOrder(Order order) {
        this.orderQueue.put(order.getOrderId(), order);
        return order;
    }

    @Override
    public Order[] getQueue() {
        return this.orderQueue.values().toArray(new Order[0]);
    }

    @Override
    public Boolean cancelOrder(Integer orderId) {
        this.orderQueue.remove(orderId);
        // return amount to users --------------------
        return true;
    }

    @Override
    public Boolean sendShipmentNotification() {
        for (Order order : this.orderQueue.values()) {
            if (order.getArrivedAt().getTime() <= System.currentTimeMillis()) {
                // send notification
                this.orderQueue.remove(order.getOrderId());
                // deduct fees from users --------------------
            }
        }
        return true;
    }
}
