package com.example.cameracaffe.repos;

import com.example.cameracaffe.entities.DettaglioConsegnaEntity;
import com.example.cameracaffe.entities.embeddedKeys.EmbeddedDettaglioConsegnaKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DettaglioConsegnaRepository extends JpaRepository<DettaglioConsegnaEntity, EmbeddedDettaglioConsegnaKey> {
    List<DettaglioConsegnaEntity> findByConsegna_Id(long id);
}
