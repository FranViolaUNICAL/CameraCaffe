package com.example.cameracaffe.services.richieste;

import com.example.cameracaffe.entities.richieste.RichiestaEntity;
import com.example.cameracaffe.entities.richieste.RichiestaInstallazioneEntity;
import com.example.cameracaffe.entities.richieste.RichiestaManutezioneEntity;
import com.example.cameracaffe.repos.richieste.RichiestaInstallazioneRepository;
import com.example.cameracaffe.repos.richieste.RichiestaManutenzioneRepository;
import com.example.cameracaffe.repos.richieste.RichiestaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RichiestaService {
    private RichiestaRepository richiestaRepository;
    private RichiestaManutenzioneRepository richiestaManutenzioneRepository;
    private RichiestaInstallazioneRepository richiestaInstallazioneRepository;

    public RichiestaService(RichiestaRepository richiestaRepository, RichiestaInstallazioneRepository richiestaInstallazioneRepository, RichiestaManutenzioneRepository richiestaManutenzioneRepository) {
        this.richiestaRepository = richiestaRepository;
        this.richiestaManutenzioneRepository = richiestaManutenzioneRepository;
        this.richiestaInstallazioneRepository = richiestaInstallazioneRepository;
    }

    public List<RichiestaInstallazioneEntity> findAllInstallazioni() {
        return richiestaInstallazioneRepository.findAll();
    }

    public List<RichiestaManutezioneEntity> findAllManutenzioni() {
        return richiestaManutenzioneRepository.findAll();
    }

    public List<RichiestaEntity> findAll() {
        return richiestaRepository.findAll();
    }

    public void save(RichiestaEntity richiestaEntity) {
        richiestaRepository.save(richiestaEntity);
    }

    public void saveManutenzione(RichiestaManutezioneEntity richiestaManutezioneEntity) {
        richiestaManutenzioneRepository.save(richiestaManutezioneEntity);
    }

    public void saveInstallazione(RichiestaInstallazioneEntity richiestaInstallazioneEntity) {
        richiestaInstallazioneRepository.save(richiestaInstallazioneEntity);
    }

    public void delete(RichiestaEntity richiestaEntity) {
        richiestaRepository.delete(richiestaEntity);
    }

    public void deleteManutenzione(RichiestaManutezioneEntity richiestaManutezioneEntity) {
        richiestaManutenzioneRepository.delete(richiestaManutezioneEntity);
    }

    public void deleteInstallazione(RichiestaInstallazioneEntity richiestaInstallazioneEntity) {
        richiestaInstallazioneRepository.delete(richiestaInstallazioneEntity);
    }

    public List<RichiestaManutezioneEntity> findRichiesteManutenzioneNonGestite() {
        return richiestaManutenzioneRepository.findRichiesteManutenzioneNonGestite();
    }

    public List<RichiestaInstallazioneEntity> findRichiesteInstallazioneNonGestite() {
        return richiestaInstallazioneRepository.findRichiesteInstallazioneNonGestite();
    }

    public List<RichiestaManutezioneEntity> findRichiestaManutenzioneByCliente_partitaIva(String partitaIva) {
        return richiestaManutenzioneRepository.findRichiesteByCliente_pIva(partitaIva);
    }

    public List<RichiestaInstallazioneEntity> findByRichiestaInstallazioneByCliente_partitaIva(String partitaIva) {
        return richiestaInstallazioneRepository.findRichiesteByCliente_pIva(partitaIva);
    }
}
