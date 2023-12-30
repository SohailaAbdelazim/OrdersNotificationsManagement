package com.service;

import com.model.Customer;

public class MessageDecorator implements IMessageAPIService {
    private IMessageAPIService wrappedMessageService = null;

    @Override
    public Boolean send(Customer customer, String subject, String content) {
        if (wrappedMessageService == null) {
            return true;
        }
        return wrappedMessageService.send(customer, subject, content);
    }

    @Override
    public void setWrappedMessageService(IMessageAPIService wrappedMessageService) {
        this.wrappedMessageService = wrappedMessageService;
    }
}
