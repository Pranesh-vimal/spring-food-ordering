package com.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.request.RequestContextHolder;

import com.model.Order;
import com.paypal.api.payments.Payment;
import com.paypal.base.rest.PayPalRESTException;
import com.service.CartService;
import com.service.OrderService;
import com.service.PaypalService;

@Controller
@RequestMapping("/payment")
public class PaypalController {

    @Autowired
    private PaypalService paypalService;

    @Autowired
    private OrderService orderService;

    @Autowired
    private CartService cartService;

    public static final String SUCCESS_URL = "pay/success";
    public static final String CANCEL_URL = "pay/cancel";

    @GetMapping(value = CANCEL_URL)
    public String cancelPay(Model model, @RequestParam("orderId") int orderId) {
        model.addAttribute("title", "Payment Cancelled");
        Order order = orderService.findById(orderId);

        if (order != null) {

            order.setStatus("Cancelled");
            order.setPayment("Failded");
            order.setTransaction_id("");
            orderService.update(order);
        }
        return "cancel";
    }

    @GetMapping(value = SUCCESS_URL)
    public String successPay(@RequestParam("paymentId") String paymentId, @RequestParam("PayerID") String payerId,
            Model model) {
        String session = RequestContextHolder.currentRequestAttributes().getSessionId();
        try {
            Payment payment = paypalService.executePayment(paymentId, payerId);

            Order order = orderService.findById(Integer.parseInt(payment.getTransactions().get(0).getDescription()));

            if (payment.getState().equals("approved")) {

                order.setPayment("PAID");
                order.setTransaction_id(payment.getId());
                orderService.save(order);
                cartService.clearCart(session);
                model.addAttribute("order", order);
                model.addAttribute("title", "Payment Success");
                return "success";
            }

            order.setPayment("Pending");
            orderService.save(order);
        } catch (PayPalRESTException e) {
            System.out.println(e.getMessage());
        }
        return "redirect:/";
    }

}