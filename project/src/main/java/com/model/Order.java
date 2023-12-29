package com.model;

import java.util.Date;
import java.util.Map;
import java.util.Vector;

public interface Order {
    public Map<Product, Integer> getProducts();
    public Vector<Customer> getCustomer();
    public Integer getOrderId();
    public Date getArrivedAt();
    public Double getShipmentFees();
    public Double getCost();
}
