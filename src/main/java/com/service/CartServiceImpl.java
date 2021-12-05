package com.service;

import com.model.Cart;
import com.model.Product;
import com.model.User;
import com.repository.CartRepository;
import com.repository.ProductRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CartServiceImpl implements CartService {

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private ProductRepository productRepository;

    @Override
    public void addProductToCart(Product product, User user, int quantity) {

    }

    @Override
    public void removeProductFromCart(Product product, User user) {

    }

    @Override
    public void updateProductQuantity(Product product, User user, int quantity) {
        // TODO Auto-generated method stub

    }

    @Override
    public void clearCart(User user) {
        // TODO Auto-generated method stub

    }

    @Override
    public void checkout(User user) {
        // TODO Auto-generated method stub

    }

    @Override
    public void removeProductFromCart(int productId, User user) {
        // TODO Auto-generated method stub

    }
}
