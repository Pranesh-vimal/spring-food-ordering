package com.web;

import java.util.Map;

import com.model.Order;
import com.repository.OrderRepository;
import com.service.OrderService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private OrderRepository orderRepository;

    @GetMapping("/orders")
    public String orderSearchForm(Model model) {
        model.addAttribute("title", "Order Search");
        return "OrderSearch";
    }

    @PostMapping("/orders")
    public String getOrder(Model model, @RequestParam Map<String, String> requestParams) {

        if(requestParams.get("orderId") == null || requestParams.get("orderId").isEmpty() || requestParams.get("email").equals("")) {
            model.addAttribute("title", "Order Search");
            model.addAttribute("error", "Please enter an order ID and email");
            return "OrderSearch";
        }

        int orderId = Integer.parseInt(requestParams.get("orderId"));
        String email = requestParams.get("email").toString();
        Order order = orderService.getOrder(orderId, email);
        model.addAttribute("order", order);
        model.addAttribute("title", "Order Details");
        return "OrderDetails";
    }

    @GetMapping("/admin/orders")
    public String findAllOrders(Model model) {
        model.addAttribute("title", "Orders");
        model.addAttribute("orders", orderService.findAllByDesc());
        return "admin/ordersList";
    }

    @GetMapping("/admin/orders/{id}")
    public String findOrderById(Model model, @PathVariable("id") int id) {
        model.addAttribute("title", "Order Details");
        model.addAttribute("order", orderService.findById(id));
        return "admin/viewOrder";
    }

    @PostMapping(value="/admin/orders/{id}")
    public String updateOrder(@PathVariable("id") int id, Model model, @RequestParam Map<String, String> requestParams) {
        Order order=orderService.findById(id);
        order.setStatus(requestParams.get("status"));
        orderRepository.save(order);
        model.addAttribute("title", "Order Details");
        model.addAttribute("message", "Order Updated Successfully");
        return "redirect:/admin/orders/"+id;
    }
    

}
