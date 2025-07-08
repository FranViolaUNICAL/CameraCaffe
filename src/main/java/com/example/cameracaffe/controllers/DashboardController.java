package com.example.cameracaffe.controllers;

import com.example.cameracaffe.DTO.ClienteDTO;
import com.example.cameracaffe.DTO.RichiestaDTO;
import com.example.cameracaffe.DTO.Roles;
import com.example.cameracaffe.DTO.TipoIntervento;
import com.example.cameracaffe.entities.PartecipazioneEntity;
import com.example.cameracaffe.entities.TecnicoEntity;
import com.example.cameracaffe.entities.UserEntity;
import com.example.cameracaffe.entities.embeddedKeys.EmbeddedPartecipazioneKey;
import com.example.cameracaffe.entities.interventi.InstallazioneEntity;
import com.example.cameracaffe.entities.interventi.InterventoEntity;
import com.example.cameracaffe.entities.interventi.ManutenzioneEntity;
import com.example.cameracaffe.entities.prodotti.DistributoreEntity;
import com.example.cameracaffe.entities.richieste.RichiestaEntity;
import com.example.cameracaffe.entities.richieste.RichiestaInstallazioneEntity;
import com.example.cameracaffe.entities.richieste.RichiestaManutezioneEntity;
import com.example.cameracaffe.services.CustomUserDetailsService;
import com.example.cameracaffe.services.TecnicoService;
import com.example.cameracaffe.services.interventi.InstallazioneService;
import com.example.cameracaffe.services.interventi.ManutenzioniService;
import com.example.cameracaffe.services.prodotti.ProdottoService;
import com.example.cameracaffe.services.richieste.RichiestaService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.parameters.P;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/dashboard")
public class DashboardController {

    private CustomUserDetailsService userDetailsService;
    private RichiestaService richiestaService;
    private ManutenzioniService manutenzioniService;
    private InstallazioneService installazioneService;
    private TecnicoService tecnicoService;
    private PasswordEncoder passwordEncoder;
    private ProdottoService prodottoService;

    public DashboardController(ProdottoService prodottoService,CustomUserDetailsService userDetailsService, RichiestaService richiestaService, ManutenzioniService manutenzioniService, InstallazioneService installazioneService, TecnicoService tecnicoService, PasswordEncoder passwordEncoder) {
        this.userDetailsService = userDetailsService;
        this.richiestaService = richiestaService;
        this.manutenzioniService = manutenzioniService;
        this.installazioneService = installazioneService;
        this.tecnicoService = tecnicoService;
        this.passwordEncoder = passwordEncoder;
        this.prodottoService = prodottoService;
    }

    @GetMapping()
    public String dashboard(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetails = userDetailsService.loadUserByUsername(auth.getName());
        UserEntity user = userDetailsService.findByEmail(userDetails.getUsername());
        Collection<? extends GrantedAuthority> authorities = auth.getAuthorities();

        if (authorities.stream().anyMatch(a -> a.getAuthority().equals("CUSTOMER"))) {
            model.addAttribute("ruolo", "CUSTOMER");
            List<RichiestaManutezioneEntity> richiesteCliente = richiestaService.findRichiestaManutenzioneByCliente_partitaIva(user.getPartitaIva());
            List<RichiestaInstallazioneEntity> richiesteInstallazioneCliente = richiestaService.findByRichiestaInstallazioneByCliente_partitaIva(user.getPartitaIva());
            List<RichiestaDTO> richieste = new ArrayList<>();
            for(RichiestaManutezioneEntity rm : richiesteCliente){
                RichiestaDTO r = new RichiestaDTO();
                r.setData(rm.getData());
                r.setDescrizione(rm.getDescrizione());
                r.setLuogo(rm.getLuogo());
                r.setTipoIntervento(TipoIntervento.MANUTENZIONE);
                richieste.add(r);
            }
            for(RichiestaInstallazioneEntity rm : richiesteInstallazioneCliente){
                RichiestaDTO r = new RichiestaDTO();
                r.setData(rm.getData());
                r.setDescrizione(rm.getDescrizione());
                r.setLuogo(rm.getLuogo());
                r.setTipoIntervento(TipoIntervento.INSTALLAZIONE);
                richieste.add(r);
            }
            model.addAttribute("richieste", richieste);
            return "dashboard";
        } else if (authorities.stream().anyMatch(a -> a.getAuthority().equals("EMPLOYEE"))) {
            model.addAttribute("ruolo", "EMPLOYEE");
            List<InstallazioneEntity> installazioniTecnico = installazioneService.findByMatricolaTecnico(user.getMatricolaTecnico());
            List<ManutenzioneEntity> manutenzioniTecnico = manutenzioniService.findByTecnicoMatricola(user.getMatricolaTecnico());
            List<RichiestaDTO> richieste = new ArrayList<>();
            for(InstallazioneEntity i : installazioniTecnico){
                RichiestaDTO r = new RichiestaDTO();
                r.setData(i.getRichiestaInstallazione().getData());
                r.setDescrizione(i.getRichiestaInstallazione().getDescrizione());
                r.setLuogo(i.getRichiestaInstallazione().getLuogo());
                r.setTipoIntervento(TipoIntervento.INSTALLAZIONE);
                richieste.add(r);
            }
            for(ManutenzioneEntity m : manutenzioniTecnico){
                RichiestaDTO r = new RichiestaDTO();
                r.setData(m.getRichiestaManutezione().getData());
                r.setDescrizione(m.getRichiestaManutezione().getDescrizione());
                r.setLuogo(m.getRichiestaManutezione().getLuogo());
                r.setTipoIntervento(TipoIntervento.MANUTENZIONE);
                richieste.add(r);
            }
            model.addAttribute("richieste", richieste);
            return "dashboard";
        } else if (authorities.stream().anyMatch(a -> a.getAuthority().equals("ADMIN"))) {
            model.addAttribute("ruolo", "ADMIN");
            List<TecnicoEntity> tecnici = tecnicoService.findAll();
            List<RichiestaInstallazioneEntity> richiesteNonGestite = richiestaService.findRichiesteInstallazioneNonGestite();
            List<RichiestaManutezioneEntity> richiesteManutenzioneNonGestite = richiestaService.findRichiesteManutenzioneNonGestite();
            List<RichiestaDTO> richieste = new ArrayList<>();
            for(RichiestaInstallazioneEntity ri : richiesteNonGestite){
                RichiestaDTO r = new RichiestaDTO();
                r.setId(ri.getId());
                r.setData(ri.getData());
                r.setDescrizione(ri.getDescrizione());
                r.setLuogo(ri.getLuogo());
                r.setTipoIntervento(TipoIntervento.INSTALLAZIONE);
                r.setCliente(new ClienteDTO(ri.getCliente().getPIva(), ri.getCliente().getRagioneSociale()));
                richieste.add(r);
            }
            for(RichiestaManutezioneEntity rm : richiesteManutenzioneNonGestite){
                RichiestaDTO r = new RichiestaDTO();
                r.setId(rm.getId());
                r.setData(rm.getData());
                r.setDescrizione(rm.getDescrizione());
                r.setLuogo(rm.getLuogo());
                r.setTipoIntervento(TipoIntervento.MANUTENZIONE);
                r.setCliente(new ClienteDTO(rm.getCliente().getPIva(), rm.getCliente().getRagioneSociale()));
                richieste.add(r);
            }
            model.addAttribute("richiesteNonGestite", richieste);
            model.addAttribute("tecnici", tecnici);
            return "dashboard";
        } else if (authorities.stream().anyMatch(a -> a.getAuthority().equals("SUPPLIER"))) {
            model.addAttribute("ruolo", "SUPPLIER");
            return "dashboard";
        }
        return "index";
    }

    @PostMapping("/aggiungiTecnico")
    public String aggiungiTecnico(
            @RequestParam String nome,
            @RequestParam String cognome,
            @RequestParam String email,
            @RequestParam String password,
            @RequestParam(required = false) String specializzazione,
            RedirectAttributes redirectAttributes) {

        try {
            // 1. Creazione del tecnico
            TecnicoEntity nuovoTecnico = new TecnicoEntity();
            // Imposta eventuali altri campi del tecnico (se presenti nell'entità)
            // nuovoTecnico.setNome(nome);
            // nuovoTecnico.setCognome(cognome);
            // nuovoTecnico.setSpecializzazione(specializzazione);

            tecnicoService.save(nuovoTecnico);

            // 2. Creazione dell'account utente
            UserEntity nuovoUtente = new UserEntity();
            nuovoUtente.setEmail(email);
            nuovoUtente.setPassword(passwordEncoder.encode(password));
            nuovoUtente.setRole(Roles.EMPLOYEE); // Ruolo EMPLOYEE per i tecnici
            nuovoUtente.setMatricolaTecnico(nuovoTecnico.getMatricola()); // Collegamento con la matricola

            userDetailsService.save(nuovoUtente);

            redirectAttributes.addFlashAttribute("successMessage", "Tecnico e account creati con successo!");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", "Errore durante la creazione del tecnico: " + e.getMessage());
            return "redirect:/dashboard"; // Modifica con la tua route appropriata
        }

        return "redirect:/dashboard"; // Modifica con la tua route appropriata
    }


    //per definizione una richiesta a cui posso assegnare un tecnico é una richiesta senza intervento
    // quindi si crea intervento, partecipazione e tutto quanto
    @PostMapping("/assegnaTecnico")
    public String assegnaTecnico(
            @RequestParam long richiestaId,
            @RequestParam long tecnicoId) {
        RichiestaEntity richiesta = richiestaService.findById(richiestaId);
        RichiestaManutezioneEntity richiestaManutenzione = richiestaService.findManutezioniById(richiestaId);
        RichiestaInstallazioneEntity richiestaInstallazione = richiestaService.findInstallazioniById(richiestaId);
        DistributoreEntity distributore = prodottoService.findDistributoreById(richiesta.getIdDistributore());
        if(richiestaManutenzione != null){
            ManutenzioneEntity manutenzione = new ManutenzioneEntity();
            manutenzione.setSoluzione("Non ancora trovata.");
            manutenzione.setRichiestaManutezione(richiestaManutenzione);
            manutenzione.setData(new Date());
            manutenzione.setCliente(richiestaManutenzione.getCliente());
            manutenzione.setDistributore(distributore);
            manutenzioniService.save(manutenzione);

            PartecipazioneEntity partecipazione = new PartecipazioneEntity();
            partecipazione.setTecnico(tecnicoService.findById(tecnicoId));
            partecipazione.setIntervento(manutenzione);
            partecipazione.setId(new EmbeddedPartecipazioneKey(tecnicoId, manutenzione.getId()));
            tecnicoService.add(partecipazione);
        }else if(richiestaInstallazione != null){
            InstallazioneEntity installazione = new InstallazioneEntity();
            installazione.setRichiestaInstallazione(richiestaInstallazione);
            installazione.setDescrizione("Non ancora avviata.");
            installazione.setData(new Date());
            installazione.setCliente(richiestaInstallazione.getCliente());
            installazione.setDistributore(distributore);
            installazioneService.save(installazione);

            PartecipazioneEntity partecipazione = new PartecipazioneEntity();
            partecipazione.setTecnico(tecnicoService.findById(tecnicoId));
            partecipazione.setIntervento(installazione);
            partecipazione.setId(new EmbeddedPartecipazioneKey(tecnicoId, installazione.getId()));
            tecnicoService.add(partecipazione);
        }
        return "redirect:/dashboard";
    }
}
