package com.web;

import com.WebSecurityConfig;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/")
public class ProductController {

    @RequestMapping(method = RequestMethod.GET)
    public String index() {
        if (WebSecurityConfig.isAuthenticated()) {
            return "redirect:/admin/dashboard";
        }
        return "index";
    }
}