package com.service;

import com.model.Product;
import org.springframework.stereotype.Service;

@Service
public class ProductsService implements IProductsService{
    @Override
    public Product[] getAllProducts() {
        return new Product[0];
    }
}
