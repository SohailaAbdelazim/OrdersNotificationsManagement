package com.service;

public interface IMessageAPIService {
    public Boolean send(String userEmail, String emailSubject, String emailContent);
}
