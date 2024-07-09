package com.gcu.controller;

import com.gcu.model.User;
import com.gcu.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/user-list")
    public String listUsers(Model model) {
        model.addAttribute("users", userService.findAll());
        return "user-list"; // Returns user-list.html template
    }

    @GetMapping("/login")
    public String showLoginPage() {
        return "login"; // Returns login.html template
    }

    @PostMapping("/login")
    public String loginUser(String email, String password, Model model) {
        User user = userService.findByEmailAndPassword(email, password);
        if (user != null) {
            model.addAttribute("users", userService.findAll());
            return "user-list"; // Returns the user-list page with the correct list of users
        } else {
            model.addAttribute("loginError", true);
            return "login"; // Redirects back to login page if login fails
        }
    }

    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        model.addAttribute("user", new User());
        return "user-form"; // Returns user-form.html template for registration form
    }

    @PostMapping("/register")
    public String registerUser(User user) {
        userService.save(user);
        return "redirect:/user-list"; // Redirects to user-list page after registration
    }
}
