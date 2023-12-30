package com.service;

import java.util.HashMap;
import java.util.Map;

import com.model.Customer;

public class MessageDecoratorFactory {
    Map<String, IMessageAPIService> messageDecoratorMap = new HashMap<>();
    MessageDecoratorFactory() {
        messageDecoratorMap.put("sms", new SMSSender());
        messageDecoratorMap.put("gmail", new GmailSender());
    }
    
    public IMessageAPIService createDecorator(Customer customer) {
        IMessageAPIService messageDecorator = null;
        for(String method : customer.getNotificationMethods()) {
            if(messageDecoratorMap.containsKey(method)) {
                IMessageAPIService newMessageDecorator = messageDecoratorMap.get(method);
                newMessageDecorator.setWrappedMessageService(messageDecorator);
                messageDecorator = newMessageDecorator;
            }
        }
        return messageDecorator;
    }
}
