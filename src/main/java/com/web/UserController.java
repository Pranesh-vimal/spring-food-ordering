package com.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import com.WebSecurityConfig;
import com.model.User;
import com.service.SecurityService;
import com.service.UserService;
import com.validator.UserValidator;

@Controller
@RequestMapping("/admin")
public class UserController {
	@Autowired
	private UserService userService;

	@Autowired
	private SecurityService securityService;

	@Autowired
	private UserValidator userValidator;

	@RequestMapping(method = RequestMethod.GET)
	public String admin(Model model) {
		if (WebSecurityConfig.isAuthenticated()) {
			return "redirect:/admin/dashboard";
		}

		return "redirect:/admin/login";
	}

	@GetMapping("/registration")
	public String registration(Model model) {
		if (WebSecurityConfig.isAuthenticated()) {
			return "redirect:/admin/dashboard";
		}
		model.addAttribute("userForm", new User());

		return "auth/registration";
	}

	@PostMapping("/registration")
	public String registration(@ModelAttribute("userForm") User userForm, BindingResult bindingResult) {
		userValidator.validate(userForm, bindingResult);

		if (bindingResult.hasErrors()) {
			return "auth/registration";
		}

		userService.save(userForm);

		securityService.autoLogin(userForm.getUsername(), userForm.getPasswordConfirm());

		return "redirect:/admin/dashboard";
	}

	@GetMapping("/login")
	public String login(Model model, String error, String logout) {
		if (WebSecurityConfig.isAuthenticated()) {
			return "redirect:/admin/dashboard";
		}
		if (error != null)
			model.addAttribute("error", "Your username and password is invalid.");

		if (logout != null)
			model.addAttribute("message", "You have been logged out successfully.");

		return "auth/login";
	}

	@GetMapping({ "/dashboard" })
	public String welcome(Model model) {
		if (!WebSecurityConfig.isAuthenticated()) {
			return "redirect:/admin/login";
		}
		return "welcome";
	}
}