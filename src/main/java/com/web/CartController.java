package com.web;

import com.model.Cart;
import com.model.Order;
import com.model.Product;
import com.service.CartService;
import com.service.OrderService;
import com.service.ProductService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestContextHolder;

@RestController
@RequestMapping("/cart")
public class CartController {

    @Autowired
    private CartService cartService;

    @Autowired
    private ProductService productService;

    @Autowired
    private OrderService orderService;

    @GetMapping("/add/{id}")
    public Cart addToCart(@PathVariable("id") int id) {
        String session = RequestContextHolder.currentRequestAttributes().getSessionId();

        Product product = productService.findById(id);

        if (product != null) {
            cartService.addToCart(session, product);
            Cart cart = cartService.findBySession(session);
            return cart;
        }

        return null;
    }

    @GetMapping("")
    public Cart getCart() {
        String session = RequestContextHolder.currentRequestAttributes().getSessionId();
        Cart cart = cartService.findBySession(session);
        return cart;
    }

    @GetMapping("/remove/{id}")
    public Cart removeFromCart(@PathVariable("id") int id) {
        String session = RequestContextHolder.currentRequestAttributes().getSessionId();
        Product product = productService.findById(id);
        if (product != null) {
            cartService.removeFromCart(session, product);
            Cart cart = cartService.findBySession(session);
            return cart;
        }
        return null;
    }

    @GetMapping("/clear")
    public Cart clearCart() {
        String session = RequestContextHolder.currentRequestAttributes().getSessionId();
        cartService.clearCart(session);
        Cart cart = cartService.findBySession(session);
        return cart;
    }

    @GetMapping("/delete/{id}")
    public Cart deleteCart(@PathVariable("id") int id) {
        String session = RequestContextHolder.currentRequestAttributes().getSessionId();
        Product product = productService.findById(id);
        if (product != null) {
            cartService.deleteCart(session, product);
            Cart cart = cartService.findBySession(session);
            return cart;
        }
        return null;
    }

    @PostMapping("/checkout")
    public String checkout(@RequestParam("name") String name, @RequestParam("email") String email,
            @RequestParam("phone") String phone) {
        Order order = new Order();
        order.setName(name);
        order.setEmail(email);
        order.setPhone(phone);
        String session = RequestContextHolder.currentRequestAttributes().getSessionId();
        Cart cart = cartService.findBySession(session);
        if (cart != null) {
            orderService.checkout(cart, order);
            cartService.clearCart(session);
            return "redirect:/success";
        }
        return null;
    }
}
