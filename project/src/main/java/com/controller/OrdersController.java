package com.controller;
import com.model.Order;
import com.service.IOrdersService;
import com.service.SimpleOrdersService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/orders")
public class OrdersController {
    IOrdersService simpleOrderService = new SimpleOrdersService();
    @GetMapping("/create/simple")
    public String home() {
        Order order = simpleOrderService.createOrder();
        return "Orders";
    }
}
