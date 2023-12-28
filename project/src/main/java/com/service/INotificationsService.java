package com.service;

import java.util.PriorityQueue;

import com.model.Order;

public interface INotificationsService {
    public Boolean sendPlacementNotification(Order order);
    public Boolean sendShipmentNotification();
    public PriorityQueue<Order> getOrdersQueue();
}
