package com.service;

import com.model.*;

import java.util.Date;
import java.util.Vector;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CompoundOrdersService extends IOrdersService {
    IOrdersService ordersService;
    @Autowired
    public CompoundOrdersService(SimpleOrdersService ordersService) {
        this.ordersService = ordersService;
    }

    @Override
    public Order createOrder(OrderCreation[] orderCreation) {
        Vector<Order> orders = new Vector<>();
        for (OrderCreation order : orderCreation) {
            OrderCreation[] newSimpleOrder = new OrderCreation[1];
            newSimpleOrder[0] = order;
            Order simpleOrder = ordersService.createOrder(newSimpleOrder);
            if(order == null) {
                return null;
            }
            orders.add(simpleOrder);
        }
        // create date equals to the current date + 30 seconds
        Date date = new Date();
        date.setTime(date.getTime() + 30000);
        Order order = new CompoundOrder(orders.toArray(new Order[0]), databaseService.getLastOrderId(), date);
        databaseService.increaseLastOrderId();
        return order;
    }

    @Override
    public Boolean payShipment(Order order) {
        Vector<Customer> customers = order.getCustomer();
        Integer numberOfCustomers = customers.size();
        Double shipmentFees = order.getShipmentFees();
        Double feesPerCustomer = shipmentFees / numberOfCustomers;
        for(Customer customer : customers) {
            customer.setBalance(customer.getBalance() - feesPerCustomer);
        }
        return true;
    }

}
