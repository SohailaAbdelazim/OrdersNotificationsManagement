package com.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.model.CustomerCreation;
import com.model.Response;
import com.model.UserCredentials;
import com.service.IAuthenticationService;

@RestController
@RequestMapping("/api/users")
public class UsersController {
    @Autowired
    private IAuthenticationService authenticationService;

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
    public Response logout(@RequestBody UserCredentials user) {
        Boolean isSuccess = authenticationService.logout(user);
        Response response = new Response();
        response.setStatus(isSuccess);
        response.setMessage("Logout " + (isSuccess ? "Success" : "Failed"));
        return response;
    }
}
