package com.service;

import java.util.Vector;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.model.Customer;
import com.model.Order;
import com.model.OrderCreation;

@Service
public abstract class IOrdersService {
    @Autowired
    protected IDatabaseService databaseService;

    public abstract Order createOrder(OrderCreation[] orderCreation);
    public abstract Boolean payShipment(Order order);
    public Boolean cancelOrder(Integer orderId) {
        Order order = databaseService.getOrder(orderId);
        Double cost = order.getCost();
        Vector<Customer> customers = order.getCustomer();
        for (Customer customer : customers) {
            customer.setBalance(customer.getBalance() + cost);
        }
        databaseService.removeOrder(order.getOrderId());
        return true;
    }
}
