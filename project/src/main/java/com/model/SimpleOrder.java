package com.model;

import java.util.Date;
import java.util.Map;
import java.util.Set;
import java.util.Vector;

import com.fasterxml.jackson.annotation.JsonGetter;

public class SimpleOrder implements Order {
    private Map<Product, Integer> products;
    private Customer customer;
    private Double shipmentFees;
    private Date arrivedAt;
    private Integer orderId;
    private Double cost;

    public SimpleOrder(Map<Product, Integer> products, Customer customer, Double shipmentFees, Date arrivedAt,
            Integer orderId) {
        this.products = products;
        this.customer = customer;
        this.shipmentFees = shipmentFees;
        this.arrivedAt = arrivedAt;
        this.orderId = orderId;
        calculateCost();
    }

    @JsonGetter("products")
    public Set<Product> getProductKeys() {
        return this.products.keySet();
    }

    private void calculateCost() {
        cost = 0.0;
        for (Map.Entry<Product, Integer> entry : products.entrySet()) {
            cost += entry.getKey().getPrice() * entry.getValue();
        }
    }

    @Override
    public Double getCost() {
        return cost;
    }

    @Override
    public Map<Product, Integer> getProducts() {
        return products;
    }

    @Override
    public Vector<Customer> getCustomer() {
        Vector<Customer> customers = new Vector<>();
        customers.add(customer);
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

    public Double getShipmentFees() {
        return shipmentFees;
    }

    public void setProducts(Map<Product, Integer> products) {
        this.products = products;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public void setShipmentFees(Double shipmentFees) {
        this.shipmentFees = shipmentFees;
    }

    public void setArrivedAt(Date arrivedAt) {
        this.arrivedAt = arrivedAt;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }
}
