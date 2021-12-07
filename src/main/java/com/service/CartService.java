package com.service;

import com.model.Cart;
import com.model.Product;

public interface CartService {
    public void addToCart(String session, Product product);

    public Cart findBySession(String session);
}
