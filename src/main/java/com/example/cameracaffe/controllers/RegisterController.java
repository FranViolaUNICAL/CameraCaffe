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

    @PostMapping
    public String registerPost(
            @RequestParam String email,
            @RequestParam String password,
            @RequestParam String confirmPassword,
            @RequestParam(required = false) String partitaIva,
            @RequestParam(required = false) boolean checkCliente,
            Model model
    ) {
        // Password mismatch
        if (!password.equals(confirmPassword)) {
            model.addAttribute("passwordMismatch", true);
            return "register";
        }

        // Email già esistente
        if (customUserDetailsService.findByEmail(email) != null) {
            model.addAttribute("userAlreadyExists", true);
            return "register";
        }

        // Creazione nuovo utente
        UserEntity user = new UserEntity();
        user.setEmail(email);
        String hashed = BCrypt.hashpw(password, BCrypt.gensalt());
        user.setPassword(hashed);

        // Determinazione ruolo
        if (partitaIva == null || partitaIva.isBlank()) {
            user.setRole(Roles.EMPLOYEE);
        } else {
            if(checkCliente){
                user.setRole(Roles.CUSTOMER);
            }else{
                user.setRole(Roles.SUPPLIER);
            }
            user.setPartitaIva(partitaIva); // salva la P.IVA se vuoi
        }

        customUserDetailsService.save(user);
        model.addAttribute("registerSuccessful", true);

        return "index";
    }
}
