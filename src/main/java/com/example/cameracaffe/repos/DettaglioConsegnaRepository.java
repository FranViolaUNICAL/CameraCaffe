package com.example.cameracaffe.repos;

import com.example.cameracaffe.entities.DettaglioConsegnaEntity;
import com.example.cameracaffe.entities.EmbeddedDettaglioConsegnaKey;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DettaglioConsegnaRepository extends JpaRepository<DettaglioConsegnaEntity, EmbeddedDettaglioConsegnaKey> {

}
