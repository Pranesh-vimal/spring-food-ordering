package com.service;

import java.util.Set;

import com.model.CartItem;
import com.model.Order;
import com.model.OrderItem;
import com.repository.OrderItemRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderItemServiceImpl implements OrderItemService {

    @Autowired
    private OrderItemRepository orderItemRepository;

    @Override
    public void createOrderItem(CartItem cartItem, Order order) {

        OrderItem orderItem = new OrderItem();

        orderItem.setOrder(order);
        orderItem.setProduct(cartItem.getProduct());
        orderItem.setQuantity(cartItem.getQuantity());
        orderItem.setSubTotal(cartItem.getSubTotal());

        orderItemRepository.save(orderItem);
    }

    @Override
    public Set<OrderItem> findByOrder(Order order) {
        return orderItemRepository.findByOrder(order);
    }

}
