package com.web;

import java.util.Map;

import com.model.Cart;
import com.model.Order;
import com.model.Product;
import com.paypal.api.payments.Links;
import com.paypal.api.payments.Payment;
import com.paypal.base.rest.PayPalRESTException;
import com.service.CartService;
import com.service.OrderService;
import com.service.PaypalService;
import com.service.ProductService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
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

    @Autowired
    private PaypalService paypalService;

    @Value("${paypal.url}")
    private String url;

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
    public String checkout(@RequestBody Map<String, Object> payLoad) {
        Order order = new Order();

        order.setName(payLoad.get("name").toString());
        order.setEmail(payLoad.get("email").toString());
        order.setPhone(payLoad.get("phone").toString());
        String session = RequestContextHolder.currentRequestAttributes().getSessionId();
        Cart cart = cartService.findBySession(session);
        if (cart != null) {
            orderService.checkout(cart, order);

            try {
                Payment payment = paypalService.createPayment(order.getTotal(), "USD", "PayPal",
                        "SALE", order.getId() + "", url + "/payment/pay/cancel?orderId=" + order.getId(),
                        url + "/payment/pay/success");

                for (Links link : payment.getLinks()) {
                    if (link.getRel().equals("approval_url")) {
                        return link.getHref();
                    }
                }
            } catch (PayPalRESTException e) {
                e.printStackTrace();
            }
        }
        return null;
    }
}
