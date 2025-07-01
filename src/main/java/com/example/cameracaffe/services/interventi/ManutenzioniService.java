package com.example.cameracaffe.services.interventi;

import com.example.cameracaffe.DTO.TipoUtilizzo;
import com.example.cameracaffe.entities.UtilizzoEntity;
import com.example.cameracaffe.entities.interventi.ManutenzioneEntity;
import com.example.cameracaffe.repos.UtilizzoRepository;
import com.example.cameracaffe.repos.interventi.ManutenzioneRepository;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ManutenzioniService {
    private ManutenzioneRepository manutenzioneRepository;
    private UtilizzoRepository utilizzoRepository;

    public ManutenzioniService(ManutenzioneRepository repository, UtilizzoRepository utilizzoRepository) {
        this.manutenzioneRepository = repository;
        this.utilizzoRepository = utilizzoRepository;
    }

    public List<ManutenzioneEntity> findAll(){
        return manutenzioneRepository.findAll();
    }

    public void delete(ManutenzioneEntity manutenzione) {
        manutenzioneRepository.delete(manutenzione);
    }

    public void save(ManutenzioneEntity manutenzione) {
        manutenzioneRepository.save(manutenzione);
    }

    public List<UtilizzoEntity> findAllUtilizzi(){
        return utilizzoRepository.findAll();
    }

    public List<UtilizzoEntity> findUtilizzoByIntervento(long intervento){
        return utilizzoRepository.findByIntervento_id(intervento);
    }

    public List<UtilizzoEntity> findUtilizzoByRicambio(long ricambio){
        return utilizzoRepository.findByRicambio_id(ricambio);
    }

    public List<UtilizzoEntity> findUtilizzoByRicambioAndTipoUtilizzo(long ricambio, TipoUtilizzo tipoUtilizzo) {
        return utilizzoRepository.findByTipoUtilizzoAndRicambio_id(tipoUtilizzo, ricambio);
    }

    public List<UtilizzoEntity> findUtilizzoByInterventoAndTipoUtilizzo(long intervento, TipoUtilizzo tipoUtilizzo) {
        return utilizzoRepository.findByTipoUtilizzoAndIntervento_id(tipoUtilizzo, intervento);
    }

    public List<ManutenzioneEntity> findByTecnicoMatricola(long matricola){
        return manutenzioneRepository.findByPartecipazioni_Tecnico_Matricola(matricola);
    }



    public List<ManutenzioneEntity> findFiltered(String modello, String partitaIva){
        Specification<ManutenzioneEntity> spec = (root, query, cb) -> cb.conjunction();
        spec = spec.
                and(ManutenzioniSpecifications.hasModello(modello))
                .and(ManutenzioniSpecifications.hasCliente(partitaIva));
        return manutenzioneRepository.findAll(spec, Sort.by("data"));
    }
}
