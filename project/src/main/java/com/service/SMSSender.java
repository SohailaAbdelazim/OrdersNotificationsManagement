package com.service;

import com.model.Customer;

public class SMSSender extends MessageDecorator {
    private String adminPhoneNumber;
    private String adminPassword;

    public SMSSender() {
        this.adminPhoneNumber = "0123456789";
        this.adminPassword = "admin";
    }

    @Override
    public Boolean send(Customer customer, String subject, String content) {
        // TODO: Implement this method to send SMS via Twilio API
        String customerPhoneNumber = customer.getPhoneNumber();
        return super.send(customer, subject, content);
    }

}
