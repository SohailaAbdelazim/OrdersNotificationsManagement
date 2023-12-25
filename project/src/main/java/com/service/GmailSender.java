package com.service;

import org.springframework.stereotype.Service;

@Service
public class GmailSender implements IMessageAPIService{
    private String adminEmail;
    private String adminPassword;
    @Override
    public Boolean send(String userEmail, String emailSubject, String emailContent) {
        return null;
    }
}
