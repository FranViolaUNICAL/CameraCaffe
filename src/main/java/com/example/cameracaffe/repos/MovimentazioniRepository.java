package com.example.cameracaffe.repos;

import com.example.cameracaffe.entities.MovimentazioneUscitaEntity;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MovimentazioniRepository extends JpaRepository<MovimentazioneUscitaEntity, Long>, JpaSpecificationExecutor<MovimentazioneUscitaEntity> {
    List<MovimentazioneUscitaEntity> findAll(Specification<MovimentazioneUscitaEntity> spec, Sort sort);
}
