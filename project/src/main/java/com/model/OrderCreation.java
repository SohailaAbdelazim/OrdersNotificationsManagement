package com.model;

public class OrderCreation {
    private String username;
    private ProductCreation[] products;

    public OrderCreation(String username, ProductCreation[] products) {
        this.username = username;
        this.products = products;
    }

    public String getUsername() {
        return username;
    }

    public ProductCreation[] getProducts() {
        return products;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setProducts(ProductCreation[] products) {
        this.products = products;
    }
}
