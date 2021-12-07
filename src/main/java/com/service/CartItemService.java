package com.service;

import java.util.Set;

import com.model.Cart;
import com.model.CartItem;
import com.model.Product;

public interface CartItemService {

    void addToCart(Cart cart, Product product);

    double getTotalPrice(Cart cart);

    Set<CartItem> findByCartId(int id);

    void removeFromCart(Cart cart, Product product);

    void clearCart(Cart cart);

    void deleteCart(Cart cart, Product product);
}
