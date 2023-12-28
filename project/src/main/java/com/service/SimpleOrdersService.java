package com.service;

import com.model.*;

import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Vector;

@Service
public class SimpleOrdersService extends IOrdersService {
    @Override
    public Order createOrder(OrderCreation[] orderCreation) {
        Vector<Product> orderProducts = new Vector<Product>();
        Customer customer = databaseService.getCustomer(orderCreation[0].getUsername());
        if (customer == null) {
            return null;
        }
        Double cost = 0.0;
        for (ProductCreation product : orderCreation[0].getProducts()) {
            Product databaseProduct = databaseService.getProduct(product.getSerialNumber());
            if (databaseProduct.getStock().compareTo(product.getAmount()) < 0) {
                return null;
            }
            cost += (databaseProduct.getPrice() * product.getAmount());
            orderProducts.add(databaseProduct);
        }
        if (cost > customer.getBalance()) {
            return null;
        }
        // if all products are available, decrease the stock
        for (ProductCreation product : orderCreation[0].getProducts()) {
            Integer targetAmount = product.getAmount();
            Product targetProduct = databaseService.getProduct(product.getSerialNumber());
            targetProduct.setStock(targetProduct.getStock() - targetAmount);
        }
        // create date equals to the current date + 10 seconds
        Date date = new Date();
        date.setTime(date.getTime() + 10000);
        customer.setBalance(customer.getBalance() - cost);
        Order order = new SimpleOrder(orderProducts, customer, 50.0, date, databaseService.getLastOrderId());
        databaseService.insertNewOrder(order);
        databaseService.increaseLastOrderId();
        return order;
    }

    @Override
    public Boolean payShipment(Order order) {
        Customer customer = order.getCustomer().get(0);
        customer.setBalance(customer.getBalance() - order.getShipmentFees());
        return true;
    }
}
