package com.model;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class CompoundOrder implements Order {
    private Order[] orders;
    private Integer orderId;
    private Date arrivedAt;
    private Double cost;

    public CompoundOrder(Order[] orders, Integer orderId, Date arrivedAt) {
        this.orders = orders;
        this.orderId = orderId;
        this.arrivedAt = arrivedAt;
        calculateCost();
    }

    private void calculateCost() {
        cost = 0.0;
        for (Order order : orders) {
            cost += order.getCost();
        }
    }

    @Override
    public Double getCost() {
        return cost;
    }

    @JsonIgnore
    @Override
    public Map<Product, Integer> getProducts() {
        Map<Product, Integer> products = new HashMap<>();
        for (Order order : orders) {
            Map<Product, Integer> subProducts = order.getProducts();
            for (Map.Entry<Product, Integer> entry : subProducts.entrySet()) {
                products.put(entry.getKey(), entry.getValue());
            }
        }
        return products;
    }

    @JsonIgnore
    @Override
    public Vector<Customer> getCustomer() {
        Vector<Customer> customers = new Vector<>();
        for (Order order : orders) {
            Vector<Customer> customerArray = order.getCustomer();
            for (Customer customer : customerArray) {
                customers.add(customer);
            }
        }
        return customers;
    }

    @Override
    public Integer getOrderId() {
        return orderId;
    }

    @Override
    public Date getArrivedAt() {
        return arrivedAt;
    }

    public void setOrders(Order[] orders) {
        this.orders = orders;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public void setArrivedAt(Date arrivedAt) {
        this.arrivedAt = arrivedAt;
    }

    public Order[] getOrders() {
        return orders;
    }

    public Double getShipmentFees() {
        String location = null;
        Boolean inSameLocation = true;
        Double fees = 0.0;

        Vector<Customer> customers = getCustomer();
        for (Customer customer : customers) {
            if (location == null)
                location = customer.getLocation();
            else {
                if (!location.equals(customer.getLocation())) {
                    inSameLocation = false;
                    break;
                }
            }
        }

        if (inSameLocation)
            return orders[0].getShipmentFees() / customers.size();

        for (Order order : orders) {
            fees += order.getShipmentFees();
        }
        return fees / customers.size();
    }
}
