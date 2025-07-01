package com.example.cameracaffe.repos;

import com.example.cameracaffe.DTO.TipoUtilizzo;
import com.example.cameracaffe.entities.UtilizzoEntity;
import com.example.cameracaffe.entities.embeddedKeys.EmbeddedUtilizzoKey;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UtilizzoRepository extends JpaRepository<UtilizzoEntity, EmbeddedUtilizzoKey> {
    List<UtilizzoEntity> findByRicambio_id(long id);
    List<UtilizzoEntity> findByIntervento_id(long id);

    List<UtilizzoEntity> findByTipoUtilizzoAndRicambio_id(TipoUtilizzo tipoUtilizzo, Long ricambioId);
    List<UtilizzoEntity> findByTipoUtilizzoAndIntervento_id(TipoUtilizzo tipoUtilizzo, Long interventoId);
}
