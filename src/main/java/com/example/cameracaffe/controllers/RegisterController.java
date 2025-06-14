package com.example.cameracaffe.controllers;

import com.example.cameracaffe.DTO.Roles;
import com.example.cameracaffe.entities.ClienteEntity;
import com.example.cameracaffe.entities.FornitoreEntity;
import com.example.cameracaffe.entities.UserEntity;
import com.example.cameracaffe.services.ClienteService;
import com.example.cameracaffe.services.CustomUserDetailsService;
import com.example.cameracaffe.services.FornitoreService;
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
    private FornitoreService fornitoreService;
    private ClienteService clienteService;


    public RegisterController(CustomUserDetailsService customUserDetailsService, FornitoreService fornitoreService, ClienteService clienteService) {
        this.customUserDetailsService = customUserDetailsService;
        this.fornitoreService = fornitoreService;
        this.clienteService = clienteService;
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
            @RequestParam String role, // SUPPLIER o CUSTOMER
            @RequestParam(required = false) String partitaIvaSupplier,
            @RequestParam(required = false) String nomeReferente,
            @RequestParam(required = false) String cognomeReferente,
            @RequestParam(required = false) String partitaIvaCustomer,
            @RequestParam(required = false) String ragioneSociale,
            Model model
    ) {
        // Controllo password
        if (!password.equals(confirmPassword)) {
            model.addAttribute("passwordMismatch", true);
            return "register";
        }

        // Controllo email esistente
        if (customUserDetailsService.findByEmail(email) != null) {
            model.addAttribute("userAlreadyExists", true);
            return "register";
        }

        // Creazione utente
        UserEntity user = new UserEntity();
        user.setEmail(email);
        String hashed = BCrypt.hashpw(password, BCrypt.gensalt());
        user.setPassword(hashed);

        if ("SUPPLIER".equals(role)) {
            user.setRole(Roles.SUPPLIER);

            // Crea FornitoreEntity
            FornitoreEntity fornitore = new FornitoreEntity();
            fornitore.setPartitaIva(partitaIvaSupplier);
            fornitore.setNomeReferente(nomeReferente);
            fornitore.setCognomeReferente(cognomeReferente);
            fornitore.setEmail(email);

            // Salva Fornitore e associa a user (se hai relazione)
            fornitoreService.save(fornitore);
            user.setPartitaIva(partitaIvaSupplier);

        } else if ("CUSTOMER".equals(role)) {
            user.setRole(Roles.CUSTOMER);

            // Crea ClienteEntity
            ClienteEntity cliente = new ClienteEntity();
            cliente.setPIva(partitaIvaCustomer);
            cliente.setRagioneSociale(ragioneSociale);

            // Salva Cliente e associa a user (se hai relazione)
            clienteService.save(cliente);
            user.setPartitaIva(partitaIvaCustomer);

        } else {
            // Se non selezionato o altro, assegna ruolo default (ad es. EMPLOYEE)
            user.setRole(Roles.EMPLOYEE);
        }

        customUserDetailsService.save(user);
        model.addAttribute("registerSuccessful", true);

        return "index";
    }

}
