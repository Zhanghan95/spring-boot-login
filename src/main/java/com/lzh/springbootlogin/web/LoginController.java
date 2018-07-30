package com.lzh.springbootlogin.web;

import com.lzh.springbootlogin.domain.User;
import com.lzh.springbootlogin.domain.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    public ResponseEntity registerPost(@RequestBody User user) {
        // System.out.println(user.toString());
        try {
            List<User> userList = userRepository.findByUsername(user.getUsername());

            if (userList.isEmpty()) {
                user.setUsername(user.getUsername());
                user.setPassword(user.getPassword());
                userRepository.save(user);
                return new ResponseEntity(HttpStatus.OK);
            } else {
                return new ResponseEntity(HttpStatus.BAD_REQUEST);
            }

        } catch (Exception e) {
           return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping("/login")
    public ResponseEntity loginPage(@RequestBody User user) {
        try {
            List<User> userList = userRepository.findByUsername(user.getUsername());

            for (User foundUser: userList) {
                if (user.getUsername().equals(foundUser.getUsername()) &&
                        user.getUsername().equals(foundUser.getUsername())) {
                    return new ResponseEntity(HttpStatus.OK);
                } else {
                    return new ResponseEntity(HttpStatus.BAD_REQUEST);
                }
            }

        } catch (Exception e) {
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return null;
    }
}
