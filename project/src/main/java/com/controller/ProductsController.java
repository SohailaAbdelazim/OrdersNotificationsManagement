package com.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.model.Product;
import com.service.IDatabaseService;
import com.service.ProductsService;

@RestController
@RequestMapping("/api/products")
public class ProductsController {
    @Autowired
    private ProductsService productsService;

    @GetMapping("")
    public Product[] listAllProducts() {
        return productsService.getAllProducts();
    }
}
