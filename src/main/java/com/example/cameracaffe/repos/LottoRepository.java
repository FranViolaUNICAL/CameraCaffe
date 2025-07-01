package com.example.cameracaffe.repos;

import com.example.cameracaffe.entities.LottoEntity;
import com.example.cameracaffe.entities.embeddedKeys.EmbeddedDettaglioOrdineKey;
import com.example.cameracaffe.entities.embeddedKeys.EmbeddedLottoKey;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LottoRepository extends JpaRepository<LottoEntity, EmbeddedLottoKey> {
    List<LottoEntity> findByAlimentare_Id(long id);
    List<LottoEntity> findByKey_LottoId(long id);
}
