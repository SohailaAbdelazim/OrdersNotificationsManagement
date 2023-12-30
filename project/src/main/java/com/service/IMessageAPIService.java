package com.service;

import com.model.Customer;

public interface IMessageAPIService {
    public Boolean send(Customer customer, String subject, String content);
    public void setWrappedMessageService(IMessageAPIService wrappedMessageService);
}
