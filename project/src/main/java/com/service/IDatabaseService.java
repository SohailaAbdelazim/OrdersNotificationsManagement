package com.service;

import com.model.Customer;
import com.model.Language;
import com.model.Order;
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
    public void addUserToLoggedUsers(String username, Customer customer);
    public void addCustomer(String username, Customer customer);
    public Boolean isCustomerLogged(String username);
    public void removeLoggedCustomer(String username);
    public Order insertNewOrder(Order order);
    public Order[] getOrderQueue();
    public Order getOrder(Integer orderId);
    public Boolean removeOrder(Integer orderId);
    public void increaseTemplateUsage(Language language);
}
