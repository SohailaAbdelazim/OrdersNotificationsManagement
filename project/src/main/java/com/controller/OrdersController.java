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
import org.springframework.web.bind.annotation.GetMapping;
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

    @GetMapping("")
    public String home() {
        // Pritn the instrctions for the user that can be done in this mapping
        return "Welcome to the Orders API!" +

                "\n\nYou can use the following mappings:" +

                "\n- Post /api/orders/create/simple - Create a simple order" +
                "\n\t- Headers: username, password" +
                "\n\t- Body: products" +
                "\n\t\t- products: ProductCreation[]" +
                "\n\t\t\t- serialNumber: integer" +
                "\n\t\t\t- amount: integer" +
                "\n\t- Response: Order" +

                "\n\n- Post /api/orders/create/compound - Create a compound order" +
                "\n\t- Headers: username, password" +
                "\n\t- Body: orders" +
                "\n\t\t- orders: OrderCreation[]" +
                "\n\t\t\t- username: string" +
                "\n\t\t\t- products: ProductCreation[]" +
                "\n\t\t\t\t- serialNumber: integer" +
                "\n\t\t\t\t- amount: integer" +
                "\n\t- Response: Order" +

                "\n\n- Post /api/orders/cancel - Cancel an order" +
                "\n\t- Headers: username, password" +
                "\n\t- Body: orderId" +
                "\n\t\t- orderId: int" +
                "\n\t- Response: Response";
    }

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
        orderCreation[0] = new OrderCreation(username, products);
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
