package com.example.cameracaffe.controllers;

import com.example.cameracaffe.services.CustomUserDetailsService;
import org.apache.catalina.User;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/login")
public class LoginController {
    CustomUserDetailsService customUserDetailsService;
    AuthenticationManager authenticationManager;

    public LoginController(CustomUserDetailsService customUserDetailsService, AuthenticationManager authenticationManager) {
        this.customUserDetailsService = customUserDetailsService;
        this.authenticationManager = authenticationManager;
    }

    @GetMapping()
    public String loginPage(@RequestParam(value = "error", required = false) String error,
                            @RequestParam(value = "logout", required = false) String logout,
                            Model model) {
        if (error != null) {
            model.addAttribute("errorMsg", "Email o password errati.");
        }
        if (logout != null) {
            model.addAttribute("msg", "Logout effettuato con successo.");
        }
        return "login";
    }
}
