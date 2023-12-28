package com.model;

import java.util.Date;
import java.util.Vector;

public interface Order {
    public Vector<Product> getProducts();
    public Vector<Customer> getCustomer();
    public Integer getOrderId();
    public Date getArrivedAt();
    public Double getShipmentFees();
    public Double getCost();
}
