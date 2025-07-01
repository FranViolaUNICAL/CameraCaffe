package com.example.cameracaffe.services;

import com.example.cameracaffe.DTO.StatoOrdine;
import com.example.cameracaffe.DTO.TipologiaProdotto;
import com.example.cameracaffe.entities.DettaglioOrdineEntity;
import com.example.cameracaffe.entities.FornitoreEntity;
import com.example.cameracaffe.entities.OrdineEntity;
import com.example.cameracaffe.repos.DettaglioOrdineRepository;
import com.example.cameracaffe.repos.OrdineRepository;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class OrdineService {
    private OrdineRepository ordineRepository;
    private DettaglioOrdineRepository dettaglioOrdineRepository;

    public OrdineService(OrdineRepository ordineRepository, DettaglioOrdineRepository dettaglioOrdineRepository) {
        this.ordineRepository = ordineRepository;
        this.dettaglioOrdineRepository = dettaglioOrdineRepository;
    }

    public boolean send(OrdineEntity ordineEntity) {
        if(ordineEntity.getStatoOrdine().equals(StatoOrdine.BOZZA)){
            ordineEntity.setStatoOrdine(StatoOrdine.EMESSO);
            ordineRepository.save(ordineEntity);
            return true;
        }
        return false;
    }

    public void saveBozza(OrdineEntity ordineEntity){
        ordineEntity.setStatoOrdine(StatoOrdine.BOZZA);
        ordineRepository.save(ordineEntity);
    }

    public void delete(OrdineEntity ordineEntity) {
        ordineRepository.delete(ordineEntity);
    }

    public List<OrdineEntity> findAll(){
        return ordineRepository.findAll();
    }

    public List<OrdineEntity> findByStatoOrdine(StatoOrdine statoOrdine){
        return ordineRepository.findByStatoOrdine(statoOrdine);
    }

    public List<OrdineEntity> findByPartitaIva(String partitaIva){
        return ordineRepository.findByFornitorePartitaIva(partitaIva);
    }

    public void saveDettaglio(DettaglioOrdineEntity dettaglioOrdineEntity){
        dettaglioOrdineRepository.save(dettaglioOrdineEntity);
    }

    public void deleteDettaglio(DettaglioOrdineEntity dettaglioOrdineEntity){
        dettaglioOrdineRepository.delete(dettaglioOrdineEntity);
    }

    public List<DettaglioOrdineEntity> findAllDettagliForOrdine(long ordineId){
        return dettaglioOrdineRepository.findByOrdine_Id(ordineId);
    }

    public List<OrdineEntity> findFiltered(TipologiaProdotto tipoProdotto, StatoOrdine statoOrdine, LocalDate from, LocalDate to, String pIva){
        Specification<OrdineEntity> spec = (root, query, cb) -> cb.conjunction();
        spec = spec.and(OrdineSpecifications.dataOrdineBetween(from, to))
                .and(OrdineSpecifications.hasNomeFornitore(pIva))
                .and(OrdineSpecifications.hasStato(statoOrdine))
                .and(OrdineSpecifications.hasTipoProdotto(tipoProdotto));
        return ordineRepository.findAll(spec);
    }
}
