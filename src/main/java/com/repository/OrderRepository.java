package com.repository;

import java.util.List;

import com.model.Order;

import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {

    Order findByEmail(String email);

    Order findById(int orderId);

    List<Order> findAllByOrderByIdDesc();

}
