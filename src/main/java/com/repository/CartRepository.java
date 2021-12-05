package com.repository;

import com.model.Cart;
import com.model.Product;
import com.model.User;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepository extends JpaRepository<Cart, Long> {

    Cart findByProductAndUser(Product product, User user);

}
