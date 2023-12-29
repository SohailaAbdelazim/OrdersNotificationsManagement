package com.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.model.Customer;
import com.model.Order;
import com.service.IDatabaseService;

@Service
public class ValidationService implements IValidationService {
    @Autowired
    private IDatabaseService databaseService;

    @Override
    public Boolean checkAuthentication(String username, String password) {
        if (username == null || password == null) {
            return false;
        }
        if (!databaseService.isCustomerLogged(username)) {
            return false;
        }
        Customer customer = databaseService.getCustomers().get(username);
        if (customer != null && customer.getPassword().equals(password)) {
            return true;
        }
        return false;
    }

    @Override
    public Boolean checkStrongPassword(String password) {
        if (password == null) {
            return false;
        }
        String regex = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z]).{8,}$";
        if (password.matches(regex)) {
            return true;
        }
        return false;
    }

    @Override
    public Boolean checkEmail(String email) {
        if (email == null) {
            return false;
        }
        String regex = "^[a-zA-Z0-9+_.-]+@[a-zA-Z0-9.-]+.[a-z]{2,4}$";
        if (email.matches(regex)) {
            return true;
        }
        return false;
    }

	@Override
	public Boolean checkAuthorizationOfOrder(String username, Integer orderId) {
		Order order = databaseService.getOrder(orderId);
        if (order == null) {
            return false;
        }
        if (!order.getCustomer().get(0).getUsername().equals(username)) {
            return false;
        }
        return true;
	}
}
