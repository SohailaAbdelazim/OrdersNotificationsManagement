package com.controller;

import com.model.Order;
import com.model.OrderCreation;
import com.model.ProductCreation;
import com.model.Response;
import com.service.CompoundOrdersService;
import com.service.IOrdersService;
import com.service.SimpleOrdersService;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/orders")
public class OrdersController {
    private SimpleOrdersService simpleOrdersService;
    private CompoundOrdersService compoundOrdersService;

    @Autowired
    public OrdersController(SimpleOrdersService simpleOrdersService, CompoundOrdersService compoundOrdersService) {
        this.simpleOrdersService = simpleOrdersService;
        this.compoundOrdersService = compoundOrdersService;
    }

    @PostMapping("/create/simple")
    public Order getSimpleOrder(@RequestBody ProductCreation[] products, @RequestHeader Map<String, String> headers) {
        OrderCreation orderCreation[] = new OrderCreation[1];
        orderCreation[0] = new OrderCreation(headers.get("username"), products);
        Order order = simpleOrdersService.createOrder(orderCreation);
        return order;
    }

    @PostMapping("/create/compound")
    public Order getCompoundOrder(@RequestBody OrderCreation[] orders) {
        Order order = compoundOrdersService.createOrder(orders);
        return order;
    }

    @PostMapping("/cancel")
    public Response cancelOrder(@RequestBody Map<String, Integer> body) {
        IOrdersService ordersService = new SimpleOrdersService();
        Boolean isSuccess = ordersService.cancelOrder(body.get("orderId"));
        Response response = new Response();
        response.setStatus(isSuccess);
        response.setMessage("Cancelling order " + (isSuccess ? "Success" : "Failed"));
        return response;
    }
}
