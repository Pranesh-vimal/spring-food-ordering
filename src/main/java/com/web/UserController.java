package com.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import com.WebSecurityConfig;
import com.model.User;
import com.service.OrderService;
import com.service.ProductService;
import com.service.RoleService;
import com.service.SecurityService;
import com.service.UserService;
import com.validator.UserValidator;

@Controller
@RequestMapping("/admin")
public class UserController {
	@Autowired
	private UserService userService;

	@Autowired
	private RoleService roleService;

	@Autowired
	private SecurityService securityService;

	@Autowired
	private UserValidator userValidator;

	@Autowired
	private ProductService productService;

	@Autowired
	private OrderService orderService;

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
		model.addAttribute("title", "Registration");

		return "auth/registration";
	}

	@PostMapping("/registration")
	public String registration(@ModelAttribute("userForm") User userForm, BindingResult bindingResult, Model model) {
		userValidator.validate(userForm, bindingResult);
		model.addAttribute("title", "Registration");
		if (bindingResult.hasErrors()) {
			return "auth/registration";
		}
		userService.save(userForm);

		securityService.autoLogin(userForm.getUsername(), userForm.getPasswordConfirm());

		return "redirect:/admin/dashboard";
	}

	@GetMapping("/users")
	public String adminUsers(Model model) {
		model.addAttribute("users", userService.findAll());
		model.addAttribute("title", "Users");
		return "admin/userList";
	}

	@GetMapping("/users/create")
	public String adminUsersCreate(Model model) {

		model.addAttribute("userForm", new User());
		model.addAttribute("title", "Create User");
		model.addAttribute("roles", roleService.findAll());
		return "admin/userForm";
	}

	@PostMapping("/users/create")
	public String adminUsersCreate(@ModelAttribute("userForm") User userForm, BindingResult bindingResult,
			Model model) {

		userValidator.validate(userForm, bindingResult);
		model.addAttribute("title", "Create User");
		model.addAttribute("roles", roleService.findAll());
		if (bindingResult.hasErrors()) {
			return "admin/userForm";
		}
		userService.save(userForm);
		return "redirect:/admin/users";
	}

	@GetMapping("/login")
	public String login(Model model, String error, String logout) {
		if (WebSecurityConfig.isAuthenticated()) {
			return "redirect:/admin/dashboard";
		}
		model.addAttribute("title", "Login");
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

		model.addAttribute("title", "Dashboard");

		int userCount = userService.findAll().size();
		model.addAttribute("userCount", userCount);

		int productCount = productService.findAll().size();
		model.addAttribute("productCount", productCount);

		int orderCount = orderService.findAll().size();
		model.addAttribute("orderCount", orderCount);

		return "welcome";
	}

	@GetMapping("/users/{id}/edit")
	public String adminUsersEdit(@PathVariable("id") int id, Model model) {
		User user = userService.findById(id);
		user.setPassword("");
		model.addAttribute("userForm", user);
		model.addAttribute("title", "Edit User");
		model.addAttribute("roles", roleService.findAll());
		return "admin/userForm";
	}

	@PostMapping("/users/{id}/edit")
	public String adminUsersEdit(@ModelAttribute("userForm") User userForm, BindingResult bindingResult, Model model) {

		userValidator.validate(userForm, bindingResult);
		model.addAttribute("title", "Edit User");
		model.addAttribute("roles", roleService.findAll());
		if (bindingResult.hasErrors()) {
			return "admin/userForm";
		}
		userService.update(userForm);
		return "redirect:/admin/users";
	}

	@GetMapping("/users/{id}/delete")
	public String adminUsersDelete(@PathVariable("id") int id) {
		User user = userService.findById(id);
		user.setRole(null);
		userService.delete(user);
		return "redirect:/admin/users";
	}

}