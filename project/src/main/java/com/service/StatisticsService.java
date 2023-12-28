package com.service;

import com.model.Customer;
import com.model.Template;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StatisticsService implements IStatisticsService{
    @Autowired
    private IDatabaseService databaseService;

    @Override
    public String getMostEmail() {
        Map<String, Customer> customers = databaseService.getCustomers();
        Customer bestCustomer = null;
        for (Customer customer : customers.values()) {
            if (bestCustomer == null || customer.getNumberOfOrders() > bestCustomer.getNumberOfOrders()) {
                bestCustomer = customer;
            }
        }
        return bestCustomer.getEmail() == null ? bestCustomer.getEmail() : "No email";
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
