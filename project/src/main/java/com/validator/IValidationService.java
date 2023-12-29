package com.validator;

public interface IValidationService {
    public Boolean checkAuthentication(String username, String password);

    public Boolean checkStrongPassword(String password);

    public Boolean checkEmail(String email);

    public Boolean checkAuthorizationOfOrder(String username, Integer orderId);
}
