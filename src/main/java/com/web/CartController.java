package com.web;

import com.model.Product;
import com.service.CartService;
import com.service.ProductService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.request.RequestContextHolder;

@Controller
@RequestMapping("/cart")
public class CartController {

    @Autowired
    private CartService cartService;

    @Autowired
    private ProductService productService;

    @GetMapping("/add")
    public String addToCart(@RequestParam("id") int id) {
        String session = RequestContextHolder.currentRequestAttributes().getSessionId();

        Product product = productService.findById(id);

        if (product != null) {
            cartService.addToCart(session, product);
        }

        return "redirect:/";
    }
}
