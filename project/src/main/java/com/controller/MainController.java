package com.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class MainController {
    @GetMapping("")
    public String home() {
        return "Welcome to the API!" +
                "\n\nYou can use the following mappings:" +

                "\n\n" + "v".repeat(60) +
                "\n" + "-".repeat(24) + " Orders API " + "-".repeat(24) +
                "\n" + "^".repeat(60) +
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
                "\n\t- Response: Response" +

                "\n\n" + "v".repeat(60) +
                "\n" + "-".repeat(22) + " Statistics API " + "-".repeat(22) +
                "\n" + "^".repeat(60) +
                "\n- GET /api/statistics/most-email - Get the most email(s) sent" +
                "\n- GET /api/statistics/most-template - Get the most template(s) used" +

                "\n\n" + "v".repeat(60) +
                "\n" + "-".repeat(20) + " Notifications API " + "-".repeat(20) +
                "\n" + "^".repeat(60) +
                "\n- GET /api/notifications/queue - Get the orders queue" +

                "\n\n" + "v".repeat(60) +
                "\n" + "-".repeat(23) + " Products API " + "-".repeat(23) +
                "\n" + "^".repeat(60) +
                "\n- GET /api/products - Get all products" +

                "\n\n" + "v".repeat(60) +
                "\n" + "-".repeat(24) + " Users API " + "-".repeat(24) +
                "\n" + "^".repeat(60) +

                "\n\n- Post /api/users/login - Login" +
                "\n\t- Body: username, password" +
                "\n\t\t- username: string" +
                "\n\t\t- password: string" +

                "\n\n- Post /api/users/signup - Signup" +
                "\n\t- Body: username, password, name, email, language, location, balance, phone, notificationMethods" +
                "\n\t\t- username: string" +
                "\n\t\t- password: string" +
                "\n\t\t- name: string" +
                "\n\t\t- email: string" +
                "\n\t\t- language: arabic | english" +
                "\n\t\t- location: string" +
                "\n\t\t- balance: double" +
                "\n\t\t- phone: string" +
                "\n\t\t- notificationMethods: string[]" +

                "\n\n- Post /api/users/logout - Logout" +
                "\n\t- Headers: username, password" +
                "\n\t- Body: username, password" +
                "\n\t\t- username: string" +
                "\n\t\t- password: string";
    }

}
