package com.service;

import java.util.List;

import com.model.Product;

public interface ProductService {
    void save(Product product);

    Product findByName(String name);

    Product findById(int id);

    List<Product> findAll();

    void delete(Product product);

    void init();
}
