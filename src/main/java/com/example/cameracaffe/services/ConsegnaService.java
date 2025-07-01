package com.example.cameracaffe.services;

import com.example.cameracaffe.entities.ConsegnaEntity;
import com.example.cameracaffe.entities.DettaglioConsegnaEntity;
import com.example.cameracaffe.entities.prodotti.ProdottoEntity;
import com.example.cameracaffe.repos.ConsegnaRepository;
import com.example.cameracaffe.repos.DettaglioConsegnaRepository;
import com.example.cameracaffe.repos.prodotti.ProdottoRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

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

    public List<ConsegnaEntity> findAll() {
        return repository.findAll();
    }

    public ConsegnaEntity findById(Long id) {
        return repository.findById(id).orElse(null);
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

    public List<DettaglioConsegnaEntity> findDettaglioByConsegnaId(long consegnaId) {
        return dettaglioRepository.findByConsegna_Id(consegnaId);
    }
}
