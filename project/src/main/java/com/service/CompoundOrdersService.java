package com.service;

import com.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Vector;

@Service
public class CompoundOrdersService extends IOrdersService{
    private Double shipmentDeduction;
    @Autowired
    private IDatabaseService databaseService;
    @Override
    public Order createOrder(ProductCreation products[], String customerName){
        return null;
    }

    @Override
    public Boolean payShipment(Order order) {
        return null;
    }


}
