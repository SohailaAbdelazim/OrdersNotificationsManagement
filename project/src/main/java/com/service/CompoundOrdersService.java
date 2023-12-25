package com.service;

import com.model.Order;
import org.springframework.stereotype.Service;

@Service
public class CompoundOrdersService extends IOrdersService{
    private Double shipmentDeduction;
    @Override
    public Order createOrder() {
        return null;
    }

    @Override
    public Boolean payShipment() {
        return null;
    }
}
