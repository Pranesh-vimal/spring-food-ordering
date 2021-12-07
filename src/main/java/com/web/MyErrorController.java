package com.web;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MyErrorController implements ErrorController {

    @RequestMapping("/error")
    public String handleError(HttpServletRequest request, Model model) {
        Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);

        if (status != null) {
            Integer statusCode = Integer.valueOf(status.toString());

            if (statusCode == HttpStatus.NOT_FOUND.value()) {

                model.addAttribute("title", "Page not found");
                return "errors/404";
            } else if (statusCode == HttpStatus.INTERNAL_SERVER_ERROR.value()) {

                model.addAttribute("title", "Some thing went wrong");
                return "errors/500";
            } else if (statusCode == HttpStatus.FORBIDDEN.value()) {

                model.addAttribute("title", "You are not allowed to access this page");
                return "errors/403";
            }
        }

        model.addAttribute("title", "Unknown error");

        return "errors/error";
    }
}
