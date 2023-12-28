package com.model;

public class Product {
    private Integer serialNumber;
    private String name;
    private String vendor;
    private Double price;
    private Category category;
    private Integer stock;

    public Product(Integer serialNumber, String name, String vendor, Double price, Category category, Integer stock) {
        this.serialNumber = serialNumber;
        this.name = name;
        this.vendor = vendor;
        this.price = price;
        this.category = category;
        this.stock = stock;
    }

    public Integer getSerialNumber() {
        return serialNumber;
    }

    public String getName() {
        return name;
    }

    public String getVendor() {
        return vendor;
    }

    public Double getPrice() {
        return price;
    }

    public Category getCategory() {
        return category;
    }

    public Integer getStock() {
        return stock;
    }

    public void setSerialNumber(Integer serialNumber) {
        this.serialNumber = serialNumber;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setVendor(String vendor) {
        this.vendor = vendor;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }
}
