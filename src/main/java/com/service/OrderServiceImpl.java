package com.service;

import java.sql.Date;
import java.util.Set;

import com.model.Cart;
import com.model.CartItem;
import com.model.Order;
import com.repository.OrderRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderItemService orderItemService;

    @Override
    public void checkout(Cart cart, Order order) {
        order.setTotal(cart.getTotalPrice());
        order.setDate(new Date(System.currentTimeMillis()));
        order.setStatus("Created");
        orderRepository.save(order);

        Set<CartItem> cartItems = cart.getCartItems();
        for (CartItem cartItem : cartItems) {
            orderItemService.createOrderItem(cartItem, order);
        }

        order.setOrderItems(orderItemService.findByOrder(order));
        orderRepository.save(order);
    }

}
