package com.repository;

import java.util.Set;

import com.model.CartItem;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CartItemRepository extends JpaRepository<CartItem, Long> {

    CartItem findByCartIdAndProductId(int cartId, Long productId);

    Set<CartItem> findByCartId(int id);
}
