package com.service;

import com.model.Product;

public interface CartService {
    public void addToCart(String session, Product product);
}
