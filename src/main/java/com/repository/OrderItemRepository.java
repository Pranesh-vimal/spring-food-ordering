package com.repository;

import java.util.Set;

import com.model.Order;
import com.model.OrderItem;

import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {

    Set<OrderItem> findByOrder(Order order);

}
