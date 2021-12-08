package com.repository;

import com.model.Order;

import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {

    Order findByEmail(String email);

    Order findById(int orderId);

}
