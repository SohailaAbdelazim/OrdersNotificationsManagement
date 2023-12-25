package com.model;

import java.util.Date;

public class SimpleOrder implements Order{
    private Product[] products;
    private Customer customer;
    private Double shipmentFees;
    private Date arrivedAt;
    private Integer orderId;
    @Override
    public Product[] getProducts() {
        return products;
    }

    @Override
    public Customer getCustomer() {
        return customer;
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

    public void setProducts(Product[] products) {
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
