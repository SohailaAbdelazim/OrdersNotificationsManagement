package com.service;

import com.model.*;

import java.util.Date;
import java.util.Map;
import java.util.Vector;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CompoundOrdersService extends IOrdersService {
    IOrdersService simpleOrderService;

    @Autowired
    public CompoundOrdersService(SimpleOrdersService simpleOrderService, IDatabaseService databaseService) {
        super(databaseService);
        this.simpleOrderService = simpleOrderService;
    }

    @Override
    public Order createOrder(OrderCreation[] orderCreation) {
        // check if all users are in our database
        for (OrderCreation order : orderCreation) {
            if (databaseService.getCustomer(order.getUsername()) == null) {
                return null;
            }
        }

        // prepare the order
        Vector<Order> orders = new Vector<>();
        for (OrderCreation order : orderCreation) {
            OrderCreation[] newSimpleOrder = new OrderCreation[1];
            newSimpleOrder[0] = order;
            Order simpleOrder = untilCreateSimpleOrder(newSimpleOrder);
            if (order == null) {
                return null;
            }
            orders.add(simpleOrder);
        }
        
        // create date equals to the current date + 20 seconds
        Date date = new Date();
        date.setTime(date.getTime() + 20000);

        Order order = new CompoundOrder(orders.toArray(new Order[0]), databaseService.getLastOrderId(), date);
        databaseService.increaseLastOrderId();
        databaseService.insertNewOrder(order);
        return order;
    }

    @Override
    public Boolean payShipment(Order order) {
        Vector<Customer> customers = order.getCustomer();
        Double shipmentFees = order.getShipmentFees();
        for (Customer customer : customers) {
            customer.setBalance(customer.getBalance() - shipmentFees);
        }
        return true;
    }

    @Override
    protected Order untilCreateSimpleOrder(OrderCreation[] newSimpleOrder) {
        return simpleOrderService.untilCreateSimpleOrder(newSimpleOrder);
    }

    @Override
    public Boolean cancelOrder(Integer orderId) {
        CompoundOrder databaseOrder = (CompoundOrder) databaseService.getOrder(orderId);
        if (databaseOrder == null) {
            return false;
        }
        for (Order order : databaseOrder.getOrders()) {
            Double cost = order.getCost();
            Vector<Customer> customers = order.getCustomer();
            customers.get(0).setBalance(customers.get(0).getBalance() + cost);
            customers.get(0).decreaseNumberOfOrders();
            // return products amount
            Map<Product, Integer> products = order.getProducts();
            for (Map.Entry<Product, Integer> entry : products.entrySet()) {
                Product product = entry.getKey();
                product.setStock(product.getStock() + entry.getValue());
            }
        }
        databaseService.removeOrder(databaseOrder.getOrderId());
        return true;
    }

}
