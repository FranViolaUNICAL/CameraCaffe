package com.example.cameracaffe.repos.interventi;

import com.example.cameracaffe.entities.interventi.InstallazioneEntity;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InstallazioneRepository extends JpaRepository<InstallazioneEntity, Long> {
    List<InstallazioneEntity> findAll(Specification<InstallazioneEntity> spec, Sort sort);
    List<InstallazioneEntity> findByPartecipazioni_Tecnico_Matricola(long matricola);
}
