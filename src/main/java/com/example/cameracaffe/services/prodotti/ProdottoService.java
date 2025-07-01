package com.example.cameracaffe.services.prodotti;

import com.example.cameracaffe.DTO.TipologiaProdotto;
import com.example.cameracaffe.entities.CompatibilitaEntity;
import com.example.cameracaffe.entities.prodotti.AlimentareEntity;
import com.example.cameracaffe.entities.prodotti.DistributoreEntity;
import com.example.cameracaffe.entities.prodotti.ProdottoEntity;
import com.example.cameracaffe.entities.prodotti.RicambioEntity;
import com.example.cameracaffe.repos.CompatibilitaRepository;
import com.example.cameracaffe.repos.prodotti.AlimentareRepository;
import com.example.cameracaffe.repos.prodotti.DistributoreRepository;
import com.example.cameracaffe.repos.prodotti.ProdottoRepository;
import com.example.cameracaffe.repos.prodotti.RicambioRepository;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProdottoService {
    private ProdottoRepository prodottoRepository;
    private DistributoreRepository distributoreRepository;
    private RicambioRepository ricambioRepository;
    private AlimentareRepository alimentareRepository;
    private CompatibilitaRepository compatibilitaRepository;

    public ProdottoService(ProdottoRepository prodottoRepository, AlimentareRepository alimentareRepository, DistributoreRepository distributoreRepository, RicambioRepository ricambioRepository, CompatibilitaRepository compatibilitaRepository) {
        this.prodottoRepository = prodottoRepository;
        this.distributoreRepository = distributoreRepository;
        this.ricambioRepository = ricambioRepository;
        this.alimentareRepository = alimentareRepository;
        this.compatibilitaRepository = compatibilitaRepository;
    }

    public void save(ProdottoEntity prodottoEntity) {
        prodottoRepository.save(prodottoEntity);
    }
    public void delete(ProdottoEntity prodottoEntity) { prodottoRepository.delete(prodottoEntity); }
    public List<ProdottoEntity> findAll() { return prodottoRepository.findAll(); }

    public void saveDistributore(DistributoreEntity distributoreEntity) {
        distributoreRepository.save(distributoreEntity);
    }

    public void deleteDistributore(DistributoreEntity distributoreEntity) {
        distributoreRepository.delete(distributoreEntity);
    }

    public List<DistributoreEntity> findAllDistributori() {
        return distributoreRepository.findAll();
    }

    public void saveRicambio(RicambioEntity ricambioEntity) {
        ricambioRepository.save(ricambioEntity);
    }

    public void deleteRicambio(RicambioEntity ricambioEntity) {
        ricambioRepository.delete(ricambioEntity);
    }

    public List<RicambioEntity> findAllRicambio() {
        return ricambioRepository.findAll();
    }

    public void saveAlimentare(AlimentareEntity alimentareEntity) {
        alimentareRepository.save(alimentareEntity);
    }

    public void deleteAlimentare(AlimentareEntity alimentareEntity) {
        alimentareRepository.delete(alimentareEntity);
    }

    public List<AlimentareEntity> findAllAlimentare() {
        return alimentareRepository.findAll();
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

    public List<CompatibilitaEntity> findCompatibilitaByDistributore(long distributore) {
        return compatibilitaRepository.findByDistributore_id(distributore);
    }

    public List<CompatibilitaEntity> findCompatibilitaByRicambio(long ricambio) {
        return compatibilitaRepository.findByRicambio_id(ricambio);
    }

    public List<CompatibilitaEntity> findAllCompatibilita() {
        return compatibilitaRepository.findAll();
    }

    public void deleteCompatibilita(CompatibilitaEntity compatibilitaEntity) {
        compatibilitaRepository.delete(compatibilitaEntity);
    }

    public void saveCompatibilita(CompatibilitaEntity compatibilitaEntity) {
        compatibilitaRepository.save(compatibilitaEntity);
    }
}
