package com.service;

import com.model.Customer;
import com.model.Product;
import com.model.Template;

import java.util.Map;

public interface IDatabaseService {
    public Customer getCustomer(String username);
    public Product[] getProducts();
    public Product getProduct(Integer serialNumber);
    public Map<String, Customer> getCustomers();
    public Map<Template, Integer> getTemplates();
    public Integer getLastOrderId();
    public void increaseLastOrderId();
}
