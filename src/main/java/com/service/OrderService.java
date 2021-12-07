package com.service;

import com.model.Cart;
import com.model.Order;

public interface OrderService {

    void checkout(Cart cart, Order order);
}
