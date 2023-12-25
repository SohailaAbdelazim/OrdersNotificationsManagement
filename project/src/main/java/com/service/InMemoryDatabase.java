package com.service;

import com.model.Customer;
import com.model.Product;
import com.model.Template;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class InMemoryDatabase implements IDatabaseService {
    private Map<String, Customer> customers;
    private Map<Integer, Product> products;
    private Map<String, Customer> loggedUsers;
    private Map<Template, Integer> templates;
    private Integer lastOrderId = 1;
    @Override
    public Customer getCustomer(String username) {
        return null;
    }

    @Override
    public Product[] getProducts() {
        return new Product[0];
    }

    @Override
    public Product getProduct(Integer serialNumber) {
        return null;
    }

    @Override
    public Map<String, Customer> getCustomers() {
        return null;
    }

    @Override
    public Map<Template, Integer> getTemplates() {
        return null;
    }

    @Override
    public Integer getLastOrderId() {
        return null;
    }

    @Override
    public void increaseLastOrderId() {

    }
}
