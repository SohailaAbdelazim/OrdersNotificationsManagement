package com.service;

import com.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Vector;

@Service
public class SimpleOrdersService extends IOrdersService{
    @Autowired
    private IDatabaseService databaseService;

    @Override
    public Order createOrder(ProductCreation products[], String customerName) {
        Vector<Product> orderProducts = new Vector<Product>();
        Double cost = 0.0;
        for (ProductCreation product : products){
            if(databaseService.getProduct(product.getSerialNumber()).getStock().compareTo(product.getAmount()) < 0){
                return null;
            }
            cost += databaseService.getProduct(product.getSerialNumber()).getPrice();
            orderProducts.add(databaseService.getProduct(product.getSerialNumber()));
        }
        if(cost > databaseService.getCustomer(customerName).getBalance()){
            return null;
        }
        Order order = new SimpleOrder(orderProducts,databaseService.getCustomer(customerName),50.0,new Date(),databaseService.getLastOrderId());
        databaseService.increaseLastOrderId();
        databaseService.getCustomer(customerName).setBalance(databaseService.getCustomer(customerName).getBalance()-cost);
        return order;
    }

    @Override
    public Boolean payShipment(Order order) {
        databaseService.getCustomer(order.getCustomer().get(0).getUsername()).setBalance(databaseService.getCustomer(order.getCustomer().get(0).getUsername()).getBalance() - order.getShipmentFees());
        return true;
    }
}
