package com.model;

import java.util.Date;

public interface Order {
    public Product[] getProducts();
    public Customer getCustomer();
    public Integer getOrderId();
    public Date getArrivedAt();
}
