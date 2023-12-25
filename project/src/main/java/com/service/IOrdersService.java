package com.service;

import com.model.Order;
import com.model.Response;

public abstract class IOrdersService {
    public abstract Order createOrder();
    public abstract Boolean payShipment();
    public Response cancelOrder() {
        return null;
    }
}
