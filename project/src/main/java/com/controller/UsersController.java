package com.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.model.Customer;
import com.model.CustomerCreation;
import com.model.Response;
import com.model.UserCredentials;
import com.service.IAuthenticationService;
import com.service.IDatabaseService;
import com.validator.IValidationService;

@RestController
@RequestMapping("/api/users")
public class UsersController {
    @Autowired
    private IAuthenticationService authenticationService;

    @Autowired
    private IDatabaseService databaseService;

    @Autowired
    private IValidationService validationService;

    @GetMapping("")
    public String home() {
        // Pritn the instrctions for the user that can be done in this mapping
        return "Welcome to the Users API!" +
                "\n\nYou can use the following mappings:" +
                "\n- GET /api/users - Get all users" +

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

    @PostMapping("/login")
    public Response login(@RequestBody UserCredentials user) {
        Boolean isSuccess = authenticationService.login(user);
        Response response = new Response();
        response.setStatus(isSuccess);
        response.setMessage("Login " + (isSuccess ? "Success" : "Failed"));
        return response;
    }

    @PostMapping("/signup")
    public Response signup(@RequestBody CustomerCreation user) {
        Boolean isSuccess = authenticationService.signup(user);
        Response response = new Response();
        response.setStatus(isSuccess);
        response.setMessage("Signup " + (isSuccess ? "Success" : "Failed"));
        return response;
    }

    @PostMapping("/logout")
    public Response logout(@RequestBody UserCredentials user, @RequestHeader Map<String, String> headers) {
        String username = headers.get("username");
        String password = headers.get("password");
        if (!validationService.checkAuthentication(username, password)) {
            Response response = new Response();
            response.setStatus(false);
            response.setMessage("Logout Failed");
            return response;
        }
        Boolean isSuccess = authenticationService.logout(user);
        Response response = new Response();
        response.setStatus(isSuccess);
        response.setMessage("Logout " + (isSuccess ? "Success" : "Failed"));
        return response;
    }
}
