package com.example.cameracaffe.services;

import com.example.cameracaffe.entities.FatturaEntity;
import com.example.cameracaffe.repos.FatturaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FatturaService {
    private FatturaRepository fatturaRepository;

    public FatturaService(FatturaRepository fatturaRepository) {
        this.fatturaRepository = fatturaRepository;
    }

    public List<FatturaEntity> findAll() {
        return fatturaRepository.findAll();
    }

    public FatturaEntity findById(Long id) {
        return fatturaRepository.findById(id).orElse(null);
    }

    public FatturaEntity save(FatturaEntity fatturaEntity) {
        return fatturaRepository.save(fatturaEntity);
    }

    public void delete(FatturaEntity fatturaEntity) {
        fatturaRepository.delete(fatturaEntity);
    }
}
