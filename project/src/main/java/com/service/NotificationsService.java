package com.service;

import com.model.Order;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class NotificationsService implements INotificationsService{
    private Map<Integer, Order> orderQueue;
    public Boolean sendPlacementNotification(Order order) {
        return null;
    }
    @Override
    public Order insertNewOrder(Order order) {
        return null;
    }

    @Override
    public Order[] getQueue() {
        return new Order[0];
    }

    @Override
    public Boolean cancelOrder(Integer orderId) {
        return null;
    }

    @Override
    public Boolean sendShipmentNotification() {
        return null;
    }
}
