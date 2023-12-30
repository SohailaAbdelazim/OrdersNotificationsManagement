package com.service;

import org.springframework.stereotype.Service;

import com.model.Customer;

@Service
public class GmailSender extends MessageDecorator {
    private String adminEmail;
    private String adminPassword;

    public GmailSender() {
        this.adminEmail = "admin@admin.com";
        this.adminPassword = "admin";
    }

    @Override
    public Boolean send(Customer customer, String subject, String content) {
        // TODO: Implement this method to send email via Gmail SMTP server
        String customerEmail = customer.getEmail();
        return super.send(customer, subject, content);
    }
}
