package com.model;

import java.util.Date;
import java.util.Vector;

public class CompoundOrder implements Order{
    private Order[] orders;
    private Integer orderId;
    private Date arrivedAt;
    private Double cost;

    

    public CompoundOrder(Order[] orders, Integer orderId, Date arrivedAt) {
        this.orders = orders;
        this.orderId = orderId;
        this.arrivedAt = arrivedAt;
        calculateCost();
    }

    private void calculateCost(){
        cost = 0.0;
        for (Order order : orders){
            cost += order.getCost();
        }
    }

    @Override
    public Double getCost() {
        return cost;
    }

    @Override
    public Vector<Product> getProducts() {
        Vector<Product> products = new Vector<>();
        for (Order order : orders) {
            Product productArray[] = order.getProducts().toArray(new Product[0]);
            for (Product product : productArray){
                products.add(product);
            }
        }
        return products;
    }

    @Override
    public Vector<Customer> getCustomer() {
        Vector<Customer> customers = new Vector<>();
        for (Order order : orders) {
            Vector<Customer> customerArray = order.getCustomer();
            for (Customer customer : customerArray){
                customers.add(customer);
            }
        }
        return customers;
    }

    @Override
    public Integer getOrderId() {
        return orderId;
    }

    @Override
    public Date getArrivedAt() {
        return arrivedAt;
    }

    public void setOrders(Order[] orders) {
        this.orders = orders;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public void setArrivedAt(Date arrivedAt) {
        this.arrivedAt = arrivedAt;
    }

    public Order[] getOrders() {
        return orders;
    }

    public Double getShipmentFees() {
        Double fees = 0.0;
        for (Order order: orders){
            fees += order.getShipmentFees();
        }
        return fees;
    }
}
