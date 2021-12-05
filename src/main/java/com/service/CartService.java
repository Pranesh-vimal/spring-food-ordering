package com.service;

import com.model.Product;
import com.model.User;

public interface CartService {

    public void addProductToCart(Product product, User user, int quantity);

    public void removeProductFromCart(Product product, User user);

    public void updateProductQuantity(Product product, User user, int quantity);

    public void clearCart(User user);

    public void checkout(User user);

    public void removeProductFromCart(int productId, User user);
}
