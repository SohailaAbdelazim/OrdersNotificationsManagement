package com.service;

import com.model.CustomerCreation;
import com.model.UserCredentials;

public interface IAuthenticationService {
    public Boolean login(UserCredentials user);
    public Boolean signup(CustomerCreation customer);
    public Boolean logout(UserCredentials user);
}
