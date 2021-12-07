package com.service;

import java.util.Set;

import com.model.CartItem;
import com.model.Order;
import com.model.OrderItem;

public interface OrderItemService {

    void createOrderItem(CartItem cartItem, Order order);

    Set<OrderItem> findByOrder(Order order);
    
}
