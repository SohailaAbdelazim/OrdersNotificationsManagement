package com.service;

import com.model.ArabicTemplate;
import com.model.Category;
import com.model.Customer;
import com.model.EnglishTemplate;
import com.model.Product;
import com.model.Template;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class InMemoryDatabase implements IDatabaseService {
    // customer username with his customer
    private Map<String, Customer> customers;
    // product serial number with his product
    private Map<Integer, Product> products;
    private Map<String, Customer> loggedUsers;
    // template with his Number Of Usage
    private Map<Template, Integer> templates;
    private Integer lastOrderId;

    InMemoryDatabase() {
        customers = new HashMap<>();
        products = new HashMap<>();
        loggedUsers = new HashMap<>();
        templates = new HashMap<>();
        lastOrderId = 1;
        init();
    }

    private void init() {
        // prepare the products
        Product[] tmpProducts = new Product[10];
        tmpProducts[0] = new Product(1, "Apple", "Apple Inc.", 1.0, Category.SUPERMARKET, 100);
        tmpProducts[1] = new Product(2, "Banana", "Banana Inc.", 2.0, Category.SUPERMARKET, 100);
        tmpProducts[2] = new Product(3, "Orange", "Orange Inc.", 3.0, Category.SUPERMARKET, 100);
        tmpProducts[3] = new Product(4, "Pineapple", "Pineapple Inc.", 4.0, Category.SUPERMARKET, 100);
        tmpProducts[4] = new Product(5, "Watermelon", "Watermelon Inc.", 5.0, Category.SUPERMARKET, 100);
        tmpProducts[5] = new Product(6, "Strawberry", "Strawberry Inc.", 6.0, Category.SUPERMARKET, 100);
        tmpProducts[6] = new Product(7, "Iphone 15", "Apple Inc.", 1500.0, Category.MOBILES, 100);
        tmpProducts[7] = new Product(8, "Samsung Galaxy S20", "Samsung Inc.", 1200.0, Category.MOBILES, 100);
        tmpProducts[8] = new Product(9, "Huawei P30", "Huawei Inc.", 1000.0, Category.MOBILES, 100);
        tmpProducts[9] = new Product(10, "Xiaomi Mi 10", "Xiaomi Inc.", 800.0, Category.MOBILES, 100);
        for (Product product : tmpProducts) {
            this.products.put(product.getSerialNumber(), product);
        }

        // prepare templates
        Template[] tmpTemplates = new Template[2];
        tmpTemplates[0] = new ArabicTemplate();
        tmpTemplates[1] = new EnglishTemplate();
        for (Template template : tmpTemplates) {
            this.templates.put(template, 0);
        }
    }

    @Override
    public void addUserToLoggedUsers(String username, Customer customer) {
        loggedUsers.put(username, customer);
    }

    @Override
    public Customer getCustomer(String username) {
        return this.customers.get(username);
    }

    @Override
    public Product[] getProducts() {
        Product[] tmpProducts = new Product[products.size()];
        // iterate over the products map
        int i = 0;
        for (Map.Entry<Integer, Product> entry : products.entrySet()) {
            tmpProducts[i++] = entry.getValue();
        }
        return tmpProducts;
    }

    @Override
    public Product getProduct(Integer serialNumber) {
        return this.products.get(serialNumber);
    }

    @Override
    public Map<String, Customer> getCustomers() {
        return this.customers;
    }

    @Override
    public Map<Template, Integer> getTemplates() {
        return this.templates;
    }

    @Override
    public Integer getLastOrderId() {
        return this.lastOrderId;
    }

    @Override
    public void increaseLastOrderId() {
        this.lastOrderId++;
    }

    @Override
    public void addCustomer(String username, Customer customer) {
        this.customers.put(username, customer);
    }

    @Override
    public Boolean isCustomerLogged(String username) {
        return this.loggedUsers.containsKey(username);
    }

    @Override
    public void removeLoggedCustomer(String username) {
        this.loggedUsers.remove(username);
    }
}
