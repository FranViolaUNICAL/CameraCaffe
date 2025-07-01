package com.example.cameracaffe.repos.interventi;

import com.example.cameracaffe.entities.interventi.InstallazioneEntity;
import com.example.cameracaffe.entities.interventi.ManutenzioneEntity;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ManutenzioneRepository extends JpaRepository<ManutenzioneEntity, Long>, JpaSpecificationExecutor<ManutenzioneEntity> {
    List<ManutenzioneEntity> findAll(Specification<ManutenzioneEntity> spec, Sort sort);
    List<ManutenzioneEntity> findByPartecipazioni_Tecnico_Matricola(long matricola);

}
