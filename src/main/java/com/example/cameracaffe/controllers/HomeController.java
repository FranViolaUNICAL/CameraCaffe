package com.example.cameracaffe.controllers;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller()
@RequestMapping("/home")
public class HomeController {
    @GetMapping
    public String home(Authentication authentication, Model model) {
        model.addAttribute("isLoggedIn", authentication.isAuthenticated());
        if(authentication.isAuthenticated()) {
            model.addAttribute("username", authentication.getName());
        }
        return "index";
    }


}
