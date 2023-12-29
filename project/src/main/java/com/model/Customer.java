package com.model;

public class Customer {
    private String username;
    private String password;
    private String name;
    private String email;
    private Language language;
    private String location;
    private Double balance;
    private Integer numberOfOrders;

    public Customer(String username, String password, String name, String email, Language language, String location, Double balance) {
        this.username = username;
        this.password = password;
        this.name = name;
        this.email = email;
        this.language = language;
        this.location = location;
        this.balance = balance;
        this.numberOfOrders = 0;
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

    public Language getLanguage() {
        return language;
    }

    public String getLocation() {
        return location;
    }

    public Double getBalance() {
        return balance;
    }

    public Integer getNumberOfOrders() {
        return numberOfOrders;
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

    public void setLanguage(Language language) {
        this.language = language;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    public void setNumberOfOrders(Integer numberOfOrders) {
        this.numberOfOrders = numberOfOrders;
    }

    public void increaseNumberOfOrders() {
        this.numberOfOrders++;
    }

    public void decreaseNumberOfOrders() {
        this.numberOfOrders--;
    }
}
