package com.service;

import java.util.Vector;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.model.Customer;
import com.model.Order;
import com.model.OrderCreation;

@Service
public abstract class IOrdersService {
    protected IDatabaseService databaseService;

    protected Integer shipmentDurationInSeconds = 20;

    @Autowired
    public IOrdersService(IDatabaseService databaseService) {
        this.databaseService = databaseService;
        this.shipmentDurationInSeconds = 20;
    }

    public abstract Order createOrder(OrderCreation[] orderCreation);

    protected abstract Order untilCreateSimpleOrder(OrderCreation[] orderCreation);

    public abstract Boolean payShipment(Order order);

    public abstract Boolean cancelOrder(Integer orderId);
}
