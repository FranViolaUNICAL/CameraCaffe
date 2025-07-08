package com.example.cameracaffe.services.interventi;

import com.example.cameracaffe.entities.interventi.InstallazioneEntity;
import com.example.cameracaffe.entities.interventi.InterventoEntity;
import com.example.cameracaffe.repos.interventi.InstallazioneRepository;
import com.example.cameracaffe.repos.interventi.InterventoRepository;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InstallazioneService {
    private InstallazioneRepository installazioneRepository;
    private InterventoRepository interventoRepository;

    public InstallazioneService(InstallazioneRepository installazioneRepository, InterventoRepository interventoRepository) {
        this.installazioneRepository = installazioneRepository;
        this.interventoRepository = interventoRepository;
    }

    public List<InstallazioneEntity> findFiltered(long cliente, long sede, String modello){
        Specification<InstallazioneEntity> spec = (root, query, cb) -> cb.conjunction();
        spec = spec
                .and(InstallazioneSpecifications.hasCliente(cliente))
                .and(InstallazioneSpecifications.hasSede(sede))
                .and(InstallazioneSpecifications.hasModello(modello));
        return installazioneRepository.findAll(spec, Sort.by("data"));
    }

    public List<InstallazioneEntity> findAll(){
        return installazioneRepository.findAll();
    }

    public void addIntervento(InterventoEntity intervento){
        interventoRepository.save(intervento);
    }

    public InterventoEntity findInterventoById(long id){
        return interventoRepository.findById(id).orElse(null);
    }

    public List<InterventoEntity> findAllIntervento(){
        return interventoRepository.findAll();
    }

    public InstallazioneEntity findById(long id){
        return installazioneRepository.findById(id).orElse(null);
    }

    public void save(InstallazioneEntity installazioneEntity){
        installazioneRepository.save(installazioneEntity);
    }

    public void delete(InstallazioneEntity installazioneEntity){
        installazioneRepository.delete(installazioneEntity);
    }

    public List<InstallazioneEntity> findByMatricolaTecnico(long matricola){
        return installazioneRepository.findByPartecipazioni_Tecnico_Matricola(matricola);
    }
}
