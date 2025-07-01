package com.example.cameracaffe.services;

import com.example.cameracaffe.DTO.TipoDDT;
import com.example.cameracaffe.entities.DDTEntity;
import com.example.cameracaffe.entities.DettaglioDDTEntity;
import com.example.cameracaffe.entities.embeddedKeys.EmbeddedDettaglioDDTKey;
import com.example.cameracaffe.repos.DDTRepository;
import com.example.cameracaffe.repos.DettaglioDDTRepository;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DDTService {
    private DDTRepository ddtRepository;
    private DettaglioDDTRepository dettaglioDDTRepository;

    public DDTService(DDTRepository ddtRepository, DettaglioDDTRepository dettaglioDDTRepository) {
        this.ddtRepository = ddtRepository;
        this.dettaglioDDTRepository = dettaglioDDTRepository;
    }

    public Optional<DDTEntity> findDDTByID(long id) {
        return ddtRepository.findById(id);
    }

    public void save(DDTEntity ddtEntity) {
        ddtRepository.save(ddtEntity);
    }

    public void delete(DDTEntity ddtEntity) {
        ddtRepository.delete(ddtEntity);
    }

    public List<DDTEntity> findAll() {
        return ddtRepository.findAll();
    }

    public List<DDTEntity> findFiltered(long id, double minPeso, int quantita, TipoDDT tipoDDT, String partitaIva) {
        Specification<DDTEntity> spec = (root, query, cb) -> cb.conjunction();
        spec = spec
                .and(DDTSpecifications.hasFornitore(partitaIva))
                .and(DDTSpecifications.hasID(id))
                .and(DDTSpecifications.hasTipoDDT(tipoDDT))
                .and(DDTSpecifications.hasPesoTotaleMinimo(minPeso))
                .and(DDTSpecifications.hasQuantitaTotaleMinima(quantita));
        return ddtRepository.findAll(spec);
    }

    public void saveDettaglio(DettaglioDDTEntity ddtEntity) {
        dettaglioDDTRepository.save(ddtEntity);
    }

    public void deleteDettaglio(DettaglioDDTEntity ddtEntity) {
        dettaglioDDTRepository.delete(ddtEntity);
    }

    public Optional<DettaglioDDTEntity> findById(EmbeddedDettaglioDDTKey key) {
        return dettaglioDDTRepository.findById(key);
    }

    public List<DettaglioDDTEntity> findByDDT_Id(long id) {
        return dettaglioDDTRepository.findByDdt_Id(id);
    }
}
