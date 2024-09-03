package com.Instagram.Instagram.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.Instagram.Instagram.Service.UserService;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private UserService userService;

    @PostMapping("signin")
    public String signIn(@RequestParam String username, @RequestParam String password) {
        return userService.signIn(username, password);
    }

    @PostMapping("signup")
    public String signUp(@RequestParam String username, @RequestParam String email, @RequestParam String password) {
        return userService.signUp(username, email, password);
    }
}