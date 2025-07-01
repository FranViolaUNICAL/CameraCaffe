package com.example.cameracaffe.repos;

import com.example.cameracaffe.entities.DettaglioDDTEntity;
import com.example.cameracaffe.entities.embeddedKeys.EmbeddedDettaglioDDTKey;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DettaglioDDTRepository extends JpaRepository<DettaglioDDTEntity, EmbeddedDettaglioDDTKey> {
    List<DettaglioDDTEntity> findByDdt_Id(long ddtId);
}
