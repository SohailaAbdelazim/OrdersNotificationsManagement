package com.service;

import com.model.Order;

public interface INotificationsService {
    public Boolean sendPlacementNotification(Order order);
    public Order insertNewOrder(Order order);
    public Order[] getQueue();
    public Boolean cancelOrder(Integer orderId);
    public Boolean sendShipmentNotification();
}
