package com.service;

import java.util.List;

import com.model.Cart;
import com.model.Order;

public interface OrderService {

    void checkout(Cart cart, Order order);

    List<Order> findAll();

    Order getOrder(int orderId, String email);
}
