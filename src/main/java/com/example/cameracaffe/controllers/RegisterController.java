package com.example.cameracaffe.controllers;

import com.example.cameracaffe.DTO.Roles;
import com.example.cameracaffe.entities.UserEntity;
import com.example.cameracaffe.services.CustomUserDetailsService;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Objects;

@Controller
@RequestMapping("/register")
public class RegisterController {
    private CustomUserDetailsService customUserDetailsService;
    public RegisterController(CustomUserDetailsService customUserDetailsService) {
        this.customUserDetailsService = customUserDetailsService;
    }
    @GetMapping()
    public String register() {
        return "register";
    }

    @PostMapping()
    public String registerPost(@RequestParam String email, @RequestParam String password, @RequestParam String confirmPassword, @RequestParam String pIva, Model model) {
        if(!password.equals(confirmPassword)) {
            model.addAttribute("passwordMismatch", true);
            return "register";
        }else{
            if(customUserDetailsService.findByEmail(email) == null) {
                UserEntity user = new UserEntity();
                user.setEmail(email);
                String hashed = BCrypt.hashpw(password, BCrypt.gensalt());
                user.setPassword(hashed);
                user.setRole(Roles.CUSTOMER);
                if(Objects.equals(pIva, "")){
                    user.setRole(Roles.SUPPLIER);
                }
                customUserDetailsService.save(user);
                model.addAttribute("register-successful", true);
            }else{
                model.addAttribute("userAlreadyExists", true);
                return "register";
            }
        }
        return "index";
    }
}
