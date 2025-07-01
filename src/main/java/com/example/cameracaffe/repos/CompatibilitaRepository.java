package com.example.cameracaffe.repos;

import com.example.cameracaffe.entities.CompatibilitaEntity;
import com.example.cameracaffe.entities.embeddedKeys.EmbeddedCompatibilitaKey;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CompatibilitaRepository extends JpaRepository<CompatibilitaEntity, EmbeddedCompatibilitaKey> {
    List<CompatibilitaEntity> findByRicambio_id(long ricambioId);
    List<CompatibilitaEntity> findByDistributore_id(long distributoreId);
}
