package com.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.model.Customer;
import com.model.CustomerCreation;
import com.model.Language;
import com.model.UserCredentials;

@Service
public class AuthenticationService implements IAuthenticationService {
    @Autowired
    private IDatabaseService databaseService;

    @Override
    public Boolean login(UserCredentials user) {
        // check user in database
        Customer customer = databaseService.getCustomer(user.getUsername());
        if (customer != null && customer.getPassword().equals(user.getPassword())) {
            // add user to logged users
            databaseService.addUserToLoggedUsers(user.getUsername(), customer);
            return true;
        }
        return false;
    }

    @Override
    public Boolean signup(CustomerCreation customer) {
        // get user from database
        Customer tmpCustomer = databaseService.getCustomer(customer.getUsername());
        if (tmpCustomer == null) {
            // add user to database
            databaseService.addCustomer(customer.getUsername(), new Customer(
                    customer.getUsername(),
                    customer.getPassword(),
                    customer.getName(),
                    customer.getEmail(),
                    customer.getLanguage().equalsIgnoreCase("english") ? Language.ENGLISH : Language.ARABIC,
                    customer.getLocation(),
                    customer.getBalance()));
            return true;
        } else {
            return false;
        }
    }

    @Override
    public Boolean logout(UserCredentials user) {
        // check user in logged users
        if (databaseService.isCustomerLogged(user.getUsername())) {
            // remove user from logged users
            databaseService.removeLoggedCustomer(user.getUsername());
            return true;
        }
        return false;
    }
}
