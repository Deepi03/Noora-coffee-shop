package com.example.nooracoffeeshop.controllers;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import com.example.nooracoffeeshop.Repositories.RoleRepository;
import com.example.nooracoffeeshop.Repositories.UserRepository;
import com.example.nooracoffeeshop.model.Role;
import com.example.nooracoffeeshop.model.User;
// import com.example.nooracoffeeshop.configurations.CustomerUserDetailsService;
// import com.example.nooracoffeeshop.services.UserService;

import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.security.core.Authentication;
// import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class UserController {

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    UserRepository userRepository;

    // @GetMapping("/user")
    // public String getUser(Model model) {

    // Authentication auth = SecurityContextHolder.getContext().getAuthentication();

    // String userName = auth.getName();
    // model.addAttribute("user", userService.getUser(userName));
    // return "profile";
    // }

    // @GetMapping("/user/{id}")
    // public String userUpdate(@PathVariable Long id, Model model) {
    // model.addAttribute("user", userService.getUserById(id));
    // return "updateUser";
    // }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/register")
    public String register() {
        return "register";
    }

    @PostMapping("/register")
    public String createUser(@ModelAttribute("user") User user, HttpServletRequest request) throws ServletException {
        String password = user.getPassword();
        user.setPassword(bCryptPasswordEncoder.encode(password));
        List<Role> roles = new ArrayList<>();
        roles.add(roleRepository.findById(2).get());

        user.setRoles(roles);
        userRepository.save(user);
        request.login(user.getEmail(), password);
        return "redirect:/";
    }

    // @PostMapping("/user/{id}")
    // public String updateUser(@PathVariable Long id, @RequestParam String name,
    // @RequestParam String userName,
    // @RequestParam String address, @RequestParam String phoneNumber, @RequestParam
    // String email) {
    // this.userService.updateUser(id, name, userName, address, phoneNumber, email);

    // return "reidrect:/user";
    // }

    @GetMapping("/forgotPassword")
    public String forgotPassword() {
        return "forgotPassword";
    }

    @PostMapping("/forgotPassword")
    public String resetPassword(@RequestParam String email, @RequestParam String password) {
        // this.userService.resetPassword(email, password);
        return "redirect:/login";
    }

    // @GetMapping("/user/delete/{id}")
    // public String deleteUser(@PathVariable Long id) {
    // this.userService.deleteUser(id);
    // return "index";
    // }
}