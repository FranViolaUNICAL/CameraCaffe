package com.example.cameracaffe.controllers;

import com.example.cameracaffe.DTO.ClienteDTO;
import com.example.cameracaffe.DTO.RichiestaDTO;
import com.example.cameracaffe.DTO.TipoIntervento;
import com.example.cameracaffe.entities.TecnicoEntity;
import com.example.cameracaffe.entities.UserEntity;
import com.example.cameracaffe.entities.interventi.InstallazioneEntity;
import com.example.cameracaffe.entities.interventi.ManutenzioneEntity;
import com.example.cameracaffe.entities.richieste.RichiestaEntity;
import com.example.cameracaffe.entities.richieste.RichiestaInstallazioneEntity;
import com.example.cameracaffe.entities.richieste.RichiestaManutezioneEntity;
import com.example.cameracaffe.services.CustomUserDetailsService;
import com.example.cameracaffe.services.TecnicoService;
import com.example.cameracaffe.services.interventi.InstallazioneService;
import com.example.cameracaffe.services.interventi.ManutenzioniService;
import com.example.cameracaffe.services.richieste.RichiestaService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Controller
@RequestMapping("/dashboard")
public class DashboardController {

    private CustomUserDetailsService userDetailsService;
    private RichiestaService richiestaService;
    private ManutenzioniService manutenzioniService;
    private InstallazioneService installazioneService;
    private TecnicoService tecnicoService;

    public DashboardController(CustomUserDetailsService userDetailsService, RichiestaService richiestaService, ManutenzioniService manutenzioniService, InstallazioneService installazioneService, TecnicoService tecnicoService) {
        this.userDetailsService = userDetailsService;
        this.richiestaService = richiestaService;
        this.manutenzioniService = manutenzioniService;
        this.installazioneService = installazioneService;
        this.tecnicoService = tecnicoService;
    }

    @GetMapping("/dashboard")
    public String dashboard(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetails = userDetailsService.loadUserByUsername(auth.getName());
        UserEntity user = userDetailsService.findByEmail(userDetails.getUsername());
        Collection<? extends GrantedAuthority> authorities = auth.getAuthorities();

        if (authorities.stream().anyMatch(a -> a.getAuthority().equals("ROLE_CUSTOMER"))) {
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
            }
            for(RichiestaInstallazioneEntity rm : richiesteInstallazioneCliente){
                RichiestaDTO r = new RichiestaDTO();
                r.setData(rm.getData());
                r.setDescrizione(rm.getDescrizione());
                r.setLuogo(rm.getLuogo());
                r.setTipoIntervento(TipoIntervento.INSTALLAZIONE);
            }
            model.addAttribute("richieste", richieste);
            return "dashboard";
        } else if (authorities.stream().anyMatch(a -> a.getAuthority().equals("ROLE_EMPLOYEE"))) {
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
            }
            for(ManutenzioneEntity m : manutenzioniTecnico){
                RichiestaDTO r = new RichiestaDTO();
                r.setData(m.getRichiestaManutezione().getData());
                r.setDescrizione(m.getRichiestaManutezione().getDescrizione());
                r.setLuogo(m.getRichiestaManutezione().getLuogo());
                r.setTipoIntervento(TipoIntervento.MANUTENZIONE);
            }
            model.addAttribute("richieste", richieste);
            return "dashboard";
        } else if (authorities.stream().anyMatch(a -> a.getAuthority().equals("ROLE_ADMIN"))) {
            model.addAttribute("ruolo", "ADMIN");
            List<TecnicoEntity> tecnici = tecnicoService.findAll();
            List<RichiestaInstallazioneEntity> richiesteNonGestite = richiestaService.findRichiesteInstallazioneNonGestite();
            List<RichiestaManutezioneEntity> richiesteManutenzioneNonGestite = richiestaService.findRichiesteManutenzioneNonGestite();
            List<RichiestaDTO> richieste = new ArrayList<>();
            for(RichiestaInstallazioneEntity ri : richiesteNonGestite){
                RichiestaDTO r = new RichiestaDTO();
                r.setData(ri.getData());
                r.setDescrizione(ri.getDescrizione());
                r.setLuogo(ri.getLuogo());
                r.setTipoIntervento(TipoIntervento.INSTALLAZIONE);
                r.setCliente(new ClienteDTO(ri.getCliente().getPIva(), ri.getCliente().getRagioneSociale()));
            }
            for(RichiestaManutezioneEntity rm : richiesteManutenzioneNonGestite){
                RichiestaDTO r = new RichiestaDTO();
                r.setData(rm.getData());
                r.setDescrizione(rm.getDescrizione());
                r.setLuogo(rm.getLuogo());
                r.setTipoIntervento(TipoIntervento.MANUTENZIONE);
                r.setCliente(new ClienteDTO(rm.getCliente().getPIva(), rm.getCliente().getRagioneSociale()));
            }
            model.addAttribute("richiesteNonGestite", richieste);
            model.addAttribute("tecnici", tecnici);
            return "dashboard";
        } else if (authorities.stream().anyMatch(a -> a.getAuthority().equals("ROLE_SUPPLIER"))) {
            model.addAttribute("ruolo", "SUPPLIER");
            return "dashboard";
        }
        return "index";
    }
}
