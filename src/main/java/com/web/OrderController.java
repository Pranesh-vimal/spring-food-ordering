package com.web;

import java.util.Map;

import com.model.Order;
import com.service.OrderService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @GetMapping("/orders")
    public String orderSearchForm(Model model) {
        model.addAttribute("title", "Order Search");
        return "OrderSearch";
    }

    @PostMapping("/orders")
    public String getOrder(Model model, @RequestParam Map<String, String> requestParams) {

        int orderId = Integer.parseInt(requestParams.get("orderId"));
        String email = requestParams.get("email").toString();
        Order order = orderService.getOrder(orderId, email);
        System.out.println(order);
        model.addAttribute("order", order);
        model.addAttribute("title", "Order Details");
        return "OrderDetails";
    }

}
