package com.service;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.Set;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;

import com.Mail;
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
        order.setCreated_at(new Timestamp(System.currentTimeMillis()));
        order.setStatus("Created");
        orderRepository.save(order);

        Set<CartItem> cartItems = cart.getCartItems();
        for (CartItem cartItem : cartItems) {
            orderItemService.createOrderItem(cartItem, order);
        }

        order.setOrderItems(orderItemService.findByOrder(order));
        orderRepository.save(order);

        Mail mailer = new Mail();
        try {
            mailer.send(order);
        } catch (AddressException e) {
            e.printStackTrace();
        } catch (MessagingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Order getOrder(int orderId, String email) {

        Order order = orderRepository.findById(orderId);
        if (order != null && order.getEmail().equals(email)) {
            return order;
        }
        return null;
    }

}
