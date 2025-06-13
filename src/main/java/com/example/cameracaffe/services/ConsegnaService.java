package com.example.cameracaffe.services;

import com.example.cameracaffe.entities.ConsegnaEntity;
import com.example.cameracaffe.entities.DettaglioConsegnaEntity;
import com.example.cameracaffe.entities.ProdottoEntity;
import com.example.cameracaffe.repos.ConsegnaRepository;
import com.example.cameracaffe.repos.DettaglioConsegnaRepository;
import com.example.cameracaffe.repos.ProdottoRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public class ConsegnaService {
    private ConsegnaRepository repository;
    private ProdottoRepository prodottoRepository;
    private DettaglioConsegnaRepository dettaglioRepository;

    public ConsegnaService(ConsegnaRepository repository) {
        this.repository = repository;
    }

    @Transactional
    public void save(ConsegnaEntity consegnaEntity) {
        repository.save(consegnaEntity);
        for(DettaglioConsegnaEntity dettaglioConsegna : consegnaEntity.getDettagliConsegna()){
            ProdottoEntity p = prodottoRepository.findById(dettaglioConsegna.getProdotto().getId()).orElse(null);
            if(p != null){
                int quantitaMagazzino = p.getQuantita();
                p.setQuantita(quantitaMagazzino + dettaglioConsegna.getQuantita());
                prodottoRepository.save(p);
            }
        }
    }

    public void delete(ConsegnaEntity consegnaEntity) {
        repository.delete(consegnaEntity);
    }

    public void saveDettaglio(DettaglioConsegnaEntity dettaglioEntity) {
        dettaglioRepository.save(dettaglioEntity);
    }

    public void deleteDettaglio(DettaglioConsegnaEntity dettaglioEntity) {
        dettaglioRepository.delete(dettaglioEntity);
    }
}
