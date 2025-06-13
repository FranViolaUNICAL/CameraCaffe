package com.example.cameracaffe.services;

import com.example.cameracaffe.DTO.TipologiaProdotto;
import com.example.cameracaffe.entities.OrdineEntity;
import com.example.cameracaffe.entities.ProdottoEntity;
import com.example.cameracaffe.repos.ProdottoRepository;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProdottoService {
    private ProdottoRepository prodottoRepository;

    public ProdottoService(ProdottoRepository prodottoRepository) {
        this.prodottoRepository = prodottoRepository;
    }

    public void save(ProdottoEntity prodottoEntity) {
        prodottoRepository.save(prodottoEntity);
    }

    public List<ProdottoEntity> findByTipologia(TipologiaProdotto tipo){
        switch (tipo){
            case RICAMBIO -> {
                return prodottoRepository.findProdottoRicambio();
            }
            case DISTRIBUTORE -> {
                return prodottoRepository.findProdottoDistributore();
            }
            case ALIMENTARE -> {
                return prodottoRepository.findProdottoAlimentare();
            }
        }
        return null;
    }

    public List<ProdottoEntity> findFiltered(TipologiaProdotto tipo, boolean isAvailable, boolean needsRestock){
        Specification<ProdottoEntity> spec = (root, query, cb) -> cb.conjunction();
        spec = spec
                .and(ProdottoSpecifications.hasTipoProdotto(tipo));
        if(isAvailable){
            spec = spec.and(ProdottoSpecifications.isAvailable());
        }
        else if(needsRestock){
            spec = spec.and(ProdottoSpecifications.isNotAvailable());
        }
        return prodottoRepository.findAll(spec);
    }
}
