package com.model;

import java.util.Date;

public class CompoundOrder implements Order{
    private Order[] orders;
    private Integer orderId;
    private Date arrivedAt;
    @Override
    public Product[] getProducts() {
        return new Product[0];
    }

    @Override
    public Customer getCustomer() {
        return null;
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
}
