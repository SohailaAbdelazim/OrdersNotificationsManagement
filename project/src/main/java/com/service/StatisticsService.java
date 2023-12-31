package com.service;

import com.model.Customer;
import com.model.Template;

import java.util.Map;
import java.util.Vector;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StatisticsService implements IStatisticsService{
    @Autowired
    private IDatabaseService databaseService;

    @Override
    public String[] getMostEmail() {
        Map<String, Customer> customers = databaseService.getCustomers();
        Vector<String> bestCustomer = new Vector<>();
        Integer bestCustomerCount = 0;
        for (Customer customer : customers.values()) {
            if(customer.getNumberOfOrders() > bestCustomerCount) {
                bestCustomerCount = customer.getNumberOfOrders();
                bestCustomer = new Vector<>();
                bestCustomer.add(customer.getEmail() + " | " + customer.getPhoneNumber());
            } else if (customer.getNumberOfOrders() == bestCustomerCount) {
                bestCustomer.add(customer.getEmail() + " | " + customer.getPhoneNumber());

            }
        }
        return bestCustomer.toArray(new String[bestCustomer.size()]);
    }

    @Override
    public Template getMostTemplate() {
        Map<Template, Integer> templates = databaseService.getTemplates();
        Template bestTemplate = null;
        // iterate over the templates
        for (Template template : templates.keySet()) {
            if (bestTemplate == null || templates.get(template) > templates.get(bestTemplate)) {
                bestTemplate = template;
            }
        }
        return bestTemplate;
    }
}
