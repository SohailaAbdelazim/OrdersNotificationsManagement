package com.controller;

import com.model.CompoundOrder;
import com.model.Order;
import com.model.OrderCreation;
import com.model.ProductCreation;
import com.model.Response;
import com.service.CompoundOrdersService;
import com.service.IDatabaseService;
import com.service.IOrdersService;
import com.service.SimpleOrdersService;
import com.validator.IValidationService;

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
    protected IDatabaseService databaseService;
    private IValidationService validationService;

    @Autowired
    public OrdersController(SimpleOrdersService simpleOrdersService, CompoundOrdersService compoundOrdersService,
            IDatabaseService databaseService, IValidationService validationService) {
        this.simpleOrdersService = simpleOrdersService;
        this.compoundOrdersService = compoundOrdersService;
        this.databaseService = databaseService;
        this.validationService = validationService;
    }

    @PostMapping("/create/simple")
    public Order getSimpleOrder(@RequestBody ProductCreation[] products, @RequestHeader Map<String, String> headers) {
        String username = headers.get("username");
        String password = headers.get("password");
        if (!validationService.checkAuthentication(username, password)) {
            return null;
        }
        OrderCreation orderCreation[] = new OrderCreation[1];
        orderCreation[0] = new OrderCreation(headers.get("username"), products);
        Order order = simpleOrdersService.createOrder(orderCreation);
        return order;
    }

    @PostMapping("/create/compound")
    public Order getCompoundOrder(@RequestBody OrderCreation[] orders, @RequestHeader Map<String, String> headers) {
        String username = headers.get("username");
        String password = headers.get("password");
        if (!validationService.checkAuthentication(username, password)) {
            return null;
        }
        Order order = compoundOrdersService.createOrder(orders);
        return order;
    }

    @PostMapping("/cancel")
    public Response cancelOrder(@RequestBody Map<String, Integer> body, @RequestHeader Map<String, String> headers) {
        String username = headers.get("username");
        String password = headers.get("password");
        Integer orderId = body.get("orderId");
        if (!validationService.checkAuthentication(username, password)
                || !validationService.checkAuthorizationOfOrder(username, orderId)) {
            return null;
        }
        Order order = databaseService.getOrder(orderId);
        Boolean isSuccess;
        if (order != null) {
            if (order instanceof CompoundOrder) {
                isSuccess = compoundOrdersService.cancelOrder(orderId);
            } else {
                isSuccess = simpleOrdersService.cancelOrder(orderId);
            }
        } else {
            isSuccess = false;
        }
        Response response = new Response();
        response.setStatus(isSuccess);
        response.setMessage("Cancelling order " + (isSuccess ? "Success" : "Failed"));
        return response;
    }
}
