package com.example.cameracaffe.repos;

import com.example.cameracaffe.entities.DettaglioOrdineEntity;
import com.example.cameracaffe.entities.embeddedKeys.EmbeddedDettaglioOrdineKey;
import com.example.cameracaffe.entities.OrdineEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface DettaglioOrdineRepository extends JpaRepository<DettaglioOrdineEntity, EmbeddedDettaglioOrdineKey> {
    List<DettaglioOrdineEntity> findByOrdine_Id(long id);

    List<DettaglioOrdineEntity> ordine(OrdineEntity ordine);
}
