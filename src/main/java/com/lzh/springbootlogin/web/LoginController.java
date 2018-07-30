package com.lzh.springbootlogin.web;

import com.lzh.springbootlogin.domain.User;
import com.lzh.springbootlogin.domain.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class LoginController {

    @Autowired
    private UserRepository userRepository;

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/register")
    public String register() {
        return "register";
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping(value="/register")
    public String registerPost(@RequestBody User user) {
        // System.out.println(user.toString());
        user.setUsername(user.getUsername());
        user.setPassword(user.getPassword());
        userRepository.save(user);
        return "redirect:/login";
    }

    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }
}
