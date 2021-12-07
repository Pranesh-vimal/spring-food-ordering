package com.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class OrderController {

    @GetMapping("/orders")
    public String orderSearchForm(Model model) {
        model.addAttribute("title", "Order Search");
        return "OrderSearch";
    }

    
}
