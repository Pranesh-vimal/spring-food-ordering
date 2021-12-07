package com.service;

import java.util.Set;

import com.model.Cart;
import com.model.CartItem;
import com.model.Product;
import com.repository.CartRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CartServiceImpl implements CartService {

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private CartItemService cartItemService;

    @Override
    public void addToCart(String session, Product product) {
        Cart cart = cartRepository.findBySession(session);
        if (cart == null) {
            cart = new Cart();
            cart.setSession(session);
            cartRepository.save(cart);
        }

        cartItemService.addToCart(cart, product);

        Set<CartItem> cartItems = cartItemService.findByCartId(cart.getId());

        cart.setCartItems(cartItems);

        cart.setTotalPrice(cartItemService.getTotalPrice(cart));

        cartRepository.save(cart);
    }

    @Override
    public Cart findBySession(String session) {
        return cartRepository.findBySession(session);
    }

    @Override
    public Cart removeFromCart(String session, Product product) {
        Cart cart = cartRepository.findBySession(session);
        if (cart != null) {
            cartItemService.removeFromCart(cart, product);

            Set<CartItem> cartItems = cartItemService.findByCartId(cart.getId());

            cart.setCartItems(cartItems);

            cart.setTotalPrice(cartItemService.getTotalPrice(cart));

            cartRepository.save(cart);
        }

        return cart;
    }

    @Override
    public void clearCart(String session) {
        Cart cart = cartRepository.findBySession(session);
        if (cart != null) {
            cartItemService.clearCart(cart);

            Set<CartItem> cartItems = cartItemService.findByCartId(cart.getId());

            cart.setCartItems(cartItems);

            cart.setTotalPrice(cartItemService.getTotalPrice(cart));

            cartRepository.save(cart);
        }
    }

    @Override
    public void deleteCart(String session, Product product) {
        Cart cart = cartRepository.findBySession(session);
        if (cart != null) {
            cartItemService.deleteCart(cart, product);

            Set<CartItem> cartItems = cartItemService.findByCartId(cart.getId());

            cart.setCartItems(cartItems);

            cart.setTotalPrice(cartItemService.getTotalPrice(cart));

            cartRepository.save(cart);
        }
    }
}
