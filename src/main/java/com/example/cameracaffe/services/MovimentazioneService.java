package com.example.cameracaffe.services;

import com.example.cameracaffe.DTO.TipologiaProdotto;
import com.example.cameracaffe.entities.MovimentazioneUscitaEntity;
import com.example.cameracaffe.repos.MovimentazioniRepository;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class MovimentazioneService {
    private MovimentazioniRepository movimentazioniRepository;

    public MovimentazioneService(MovimentazioniRepository movimentazioniRepository) {
        this.movimentazioniRepository = movimentazioniRepository;
    }

    public void save(MovimentazioneUscitaEntity movimentazioneUscitaEntity) {
        movimentazioniRepository.save(movimentazioneUscitaEntity);
    }

    public void delete(MovimentazioneUscitaEntity movimentazioneUscitaEntity) {
        movimentazioniRepository.delete(movimentazioneUscitaEntity);
    }

    public Optional<MovimentazioneUscitaEntity> findById(Long id) {
        return movimentazioniRepository.findById(id);
    }

    public List<MovimentazioneUscitaEntity> findFiltered(String fornitore, LocalDate from, LocalDate to, TipologiaProdotto tipologia) {
        Specification<MovimentazioneUscitaEntity> spec = (root, query, cb) -> cb.conjunction();
        spec = spec
                .and(MovimentazioneSpecifications.hasFornitore(fornitore))
                .and(MovimentazioneSpecifications.hasTipologiaProdotto(tipologia))
                .and(MovimentazioneSpecifications.dataMovimentazioneBetween(from, to));
        return movimentazioniRepository.findAll(spec);
    }
}
