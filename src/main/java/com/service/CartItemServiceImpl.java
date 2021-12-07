package com.service;

import java.util.Set;

import com.model.Cart;
import com.model.CartItem;
import com.model.Product;
import com.repository.CartItemRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CartItemServiceImpl implements CartItemService {

    @Autowired
    private CartItemRepository cartItemRepository;

    @Override
    public void addToCart(Cart cart, Product product) {
        CartItem cartItem = cartItemRepository.findByCartIdAndProductId(cart.getId(), product.getId());

        if (cartItem == null) {
            cartItem = new CartItem();
            cartItem.setCart(cart);
            cartItem.setProduct(product);
            cartItem.setQuantity(1);
            cartItem.setSubTotal(cartItem.getProduct().getPrice());
        } else {
            cartItem.setQuantity(cartItem.getQuantity() + 1);
            cartItem.setSubTotal(cartItem.getSubTotal() + cartItem.getProduct().getPrice());
        }

        cartItemRepository.save(cartItem);
    }

    @Override
    public double getTotalPrice(Cart cart) {

        double totalPrice = 0;
        for (CartItem cartItem : cart.getCartItems()) {
            totalPrice += cartItem.getSubTotal();
        }

        return totalPrice;
    }

    @Override
    public Set<CartItem> findByCartId(int id) {
        return cartItemRepository.findByCartId(id);
    }

    @Override
    public void removeFromCart(Cart cart, Product product) {
        CartItem cartItem = cartItemRepository.findByCartIdAndProductId(cart.getId(), product.getId());

        if (cartItem != null) {
            cartItem.setQuantity(cartItem.getQuantity() - 1);
            cartItem.setSubTotal(cartItem.getSubTotal() - cartItem.getProduct().getPrice());
            cartItemRepository.save(cartItem);

            if (cartItem.getQuantity() == 0) {
                cartItemRepository.delete(cartItem);
            }
        }
    }

    @Override
    public void clearCart(Cart cart) {
        for (CartItem cartItem : cart.getCartItems()) {
            cartItemRepository.delete(cartItem);
        }
    }

    @Override
    public void deleteCart(Cart cart, Product product) {
        CartItem cartItem = cartItemRepository.findByCartIdAndProductId(cart.getId(), product.getId());
        cartItemRepository.delete(cartItem);
    }
}
