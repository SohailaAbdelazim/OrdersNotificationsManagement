package com.model;

import java.util.Date;
import java.util.Vector;

public class SimpleOrder implements Order{
    private Vector<Product> products;
    private Customer customer;
    private Double shipmentFees;
    private Date arrivedAt;
    private Integer orderId;

    public SimpleOrder(Vector<Product> products, Customer customer, Double shipmentFees, Date arrivedAt, Integer orderId) {
        this.products = products;
        this.customer = customer;
        this.shipmentFees = shipmentFees;
        this.arrivedAt = arrivedAt;
        this.orderId = orderId;
    }

    @Override
    public Vector<Product> getProducts() {
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

    public void setProducts(Vector<Product> products) {
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
