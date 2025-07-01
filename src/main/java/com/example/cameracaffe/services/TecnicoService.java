package com.example.cameracaffe.services;

import com.example.cameracaffe.entities.PartecipazioneEntity;
import com.example.cameracaffe.entities.TecnicoEntity;
import com.example.cameracaffe.repos.PartecipazioneRepository;
import com.example.cameracaffe.repos.TecnicoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TecnicoService {
    private TecnicoRepository tecnicoRepository;
    private PartecipazioneRepository partecipazioneRepository;

    public TecnicoService(TecnicoRepository tecnicoRepository, PartecipazioneRepository partecipazioneRepository) {
        this.tecnicoRepository = tecnicoRepository;
        this.partecipazioneRepository = partecipazioneRepository;
    }

    public List<TecnicoEntity> findAll(){
        return tecnicoRepository.findAll();
    }

    public void save(TecnicoEntity tecnico){
        tecnicoRepository.save(tecnico);
    }

    public void delete(TecnicoEntity tecnico){
        tecnicoRepository.delete(tecnico);
    }

    public List<PartecipazioneEntity> findPartecipazioneByIntervento(long intervento){
        return partecipazioneRepository.findByIntervento_id(intervento);
    }

    public List<PartecipazioneEntity> findPartecipazioneByTecnico(long matricola){
        return partecipazioneRepository.findByTecnico_matricola(matricola);
    }

    public List<PartecipazioneEntity> findAllPartecipazione(){
        return partecipazioneRepository.findAll();
    }

    public void delete(PartecipazioneEntity partecipazione){
        partecipazioneRepository.delete(partecipazione);
    }

    public void add(PartecipazioneEntity partecipazione){
        partecipazioneRepository.save(partecipazione);
    }
}
