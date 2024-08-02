package com.gcu.controller;

import com.gcu.model.User;
import com.gcu.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Controller
public class UserController {

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    @GetMapping("/user-list")
    public String listUsers(Model model) {
        logger.info("Entering method: listUsers");
        model.addAttribute("users", userService.findAll());
        logger.info("Exiting method: listUsers");
        return "user-list"; // Returns user-list.html template
    }

    @GetMapping("/login")
    public String showLoginPage() {
        logger.info("Entering method: showLoginPage");
        logger.info("Exiting method: showLoginPage");
        return "login"; // Returns login.html template
    }

    @PostMapping("/login")
    public String loginUser(String email, String password, Model model) {
        logger.info("Entering method: loginUser with email: {}", email);
        User user = userService.findByEmailAndPassword(email, password);
        if (user != null) {
            model.addAttribute("users", userService.findAll());
            logger.info("Exiting method: loginUser - login successful");
            return "user-list"; // Returns the user-list page with the correct list of users
        } else {
            model.addAttribute("loginError", true);
            logger.warn("Exiting method: loginUser - login failed for email: {}", email);
            return "login"; // Redirects back to login page if login fails
        }
    }

    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        logger.info("Entering method: showRegistrationForm");
        model.addAttribute("user", new User());
        logger.info("Exiting method: showRegistrationForm");
        return "user-form"; // Returns user-form.html template for registration form
    }

    @PostMapping("/register")
    public String registerUser(User user) {
        logger.info("Entering method: registerUser with user: {}", user.getEmail());
        userService.save(user);
        logger.info("Exiting method: registerUser");
        return "redirect:/user-list"; // Redirects to user-list page after registration
    }
}
