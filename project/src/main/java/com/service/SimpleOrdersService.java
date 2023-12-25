package com.service;

import com.model.Order;
import org.springframework.stereotype.Service;

@Service
public class SimpleOrdersService extends IOrdersService{
    @Override
    public Order createOrder() {
        return null;
    }

    @Override
    public Boolean payShipment() {
        return null;
    }
}
