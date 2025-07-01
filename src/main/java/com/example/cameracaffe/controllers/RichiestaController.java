package com.example.cameracaffe.controllers;

import com.example.cameracaffe.DTO.RichiestaDTO;
import com.example.cameracaffe.DTO.TipoIntervento;
import com.example.cameracaffe.entities.ClienteEntity;
import com.example.cameracaffe.entities.UserEntity;
import com.example.cameracaffe.entities.richieste.RichiestaEntity;
import com.example.cameracaffe.entities.richieste.RichiestaInstallazioneEntity;
import com.example.cameracaffe.entities.richieste.RichiestaManutezioneEntity;
import com.example.cameracaffe.repos.ClienteRepository;
import com.example.cameracaffe.services.ClienteService;
import com.example.cameracaffe.services.CustomUserDetailsService;
import com.example.cameracaffe.services.richieste.RichiestaService;
import jakarta.transaction.Transactional;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Arrays;
import java.util.logging.Logger;

@Controller
@RequestMapping("/richiesta")
public class RichiestaController {
    private static final org.slf4j.Logger log = LoggerFactory.getLogger(RichiestaController.class);
    private RichiestaService richiestaService;
    private CustomUserDetailsService customUserDetailsService;
    private ClienteService clienteService;

    public RichiestaController(RichiestaService richiestaService, CustomUserDetailsService customUserDetailsService, ClienteService clienteService) {
        this.richiestaService = richiestaService;
        this.customUserDetailsService = customUserDetailsService;
        this.clienteService = clienteService;
    }

    @GetMapping("/nuova")
    public String nuova(Model model) {
        model.addAttribute("richiesta", new RichiestaDTO());
        model.addAttribute("tipiIntervento", Arrays.asList(TipoIntervento.values()));
        return "nuova_richiesta_assistenza";
    }

    @PostMapping("/salva")
    @Transactional
    public String salva(Model model, @ModelAttribute RichiestaDTO richiesta) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetails = customUserDetailsService.loadUserByUsername(auth.getName());
        UserEntity user = customUserDetailsService.findByEmail(userDetails.getUsername());
        ClienteEntity cliente = clienteService.findByPartitaIva(user.getPartitaIva());

        RichiestaEntity r;

        if(richiesta.getTipoIntervento().equals(TipoIntervento.INSTALLAZIONE)) {
            RichiestaInstallazioneEntity rie = new RichiestaInstallazioneEntity();
            // Imposta i campi specifici per l'installazione se presenti
            r = rie;
        } else {
            RichiestaManutezioneEntity rme = new RichiestaManutezioneEntity();
            // Imposta i campi specifici per la manutenzione se presenti
            r = rme;
        }

        // Imposta i campi comuni
        r.setData(richiesta.getData());
        r.setDescrizione(richiesta.getDescrizione());
        r.setLuogo(richiesta.getLuogo());
        r.setCliente(cliente);

        richiestaService.save(r); // Salva l'entità appropriata in base al tipo

        return "redirect:/dashboard";
    }


}
