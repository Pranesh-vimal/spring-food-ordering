package com.service;

import java.util.List;
import java.util.Optional;

import com.model.Product;

public interface ProductService {
    void save(Product product);

    Product findByName(String name);

    Optional<Product> findById(Long id);

    List<Product> findAll();
}
