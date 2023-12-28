package com.service;

import com.model.Product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductsService implements IProductsService{
    @Autowired
    private IDatabaseService databaseService;

    @Override
    public Product[] getAllProducts() {
        return databaseService.getProducts();
    }
}
