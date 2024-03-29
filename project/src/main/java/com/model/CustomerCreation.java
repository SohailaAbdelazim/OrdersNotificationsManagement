package com.model;

public class CustomerCreation {
    private String username;
    private String password;
    private String name;
    private String email;
    private String language;
    private String location;
    private Double balance;
    private String phoneNumber;
    private String[] notificationMethods;

    public CustomerCreation(String username, String password, String name, String email, String language,
            String location, Double balance, String phoneNumber, String[] notificationMethods) {
        this.username = username;
        this.password = password;
        this.name = name;
        this.email = email;
        this.language = language;
        this.location = location;
        this.balance = balance;
        this.phoneNumber = phoneNumber;
        this.notificationMethods = notificationMethods;
    }

    public String[] getNotificationMethods() {
        return notificationMethods;
    }

    public void setNotificationMethods(String[] notificationMethods) {
        this.notificationMethods = notificationMethods;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getLanguage() {
        return language;
    }

    public String getLocation() {
        return location;
    }

    public Double getBalance() {
        return balance;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }
}
