package com.service;

import com.model.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

@Service
public class SimpleOrdersService extends IOrdersService {

    @Autowired
    public SimpleOrdersService(IDatabaseService databaseService) {
        super(databaseService);
    }

    @Override
    public Order createOrder(OrderCreation[] orderCreation) {
        Order order = untilCreateSimpleOrder(orderCreation);
        if(order == null){
            return null ;
        }
        databaseService.insertNewOrder(order);
        databaseService.increaseLastOrderId();
        return order;
    }

    protected Order untilCreateSimpleOrder(OrderCreation[] orderCreation) {
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

        Date date = new Date();
        date.setTime(date.getTime() + this.shipmentDurationInSeconds * 1000);
        customer.setBalance(customer.getBalance() - cost);

        Map<Product, Integer> amountProductsOrder = new HashMap<>();
        for (ProductCreation product : orderCreation[0].getProducts()) {
            amountProductsOrder.put(databaseService.getProduct(product.getSerialNumber()), product.getAmount());
        }

        Order order = new SimpleOrder(amountProductsOrder, customer, 50.0, date, databaseService.getLastOrderId());
        customer.increaseNumberOfOrders();
        return order;
    }

    @Override
    public Boolean payShipment(Order order) {
        Customer customer = order.getCustomer().get(0);
        customer.setBalance(customer.getBalance() - order.getShipmentFees());
        databaseService.increaseTemplateUsage(customer.getLanguage());
        return true;
    }

    @Override
    public Boolean cancelOrder(Integer orderId) {
        SimpleOrder order = (SimpleOrder) databaseService.getOrder(orderId);
        if (order == null) {
            return false;
        }
        Double cost = order.getCost();
        Vector<Customer> customers = order.getCustomer();
        customers.get(0).setBalance(customers.get(0).getBalance() + cost);
        customers.get(0).decreaseNumberOfOrders();
        databaseService.removeOrder(order.getOrderId());

        // return products amount
        Map<Product, Integer> products = order.getProducts();
        for (Map.Entry<Product, Integer> entry : products.entrySet()) {
            Product product = entry.getKey();
            product.setStock(product.getStock() + entry.getValue());
        }
        return true;
    }
}
