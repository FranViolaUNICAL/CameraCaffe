package com.example.cameracaffe.services.interventi;

import com.example.cameracaffe.entities.interventi.ManutenzioneEntity;
import com.example.cameracaffe.entities.prodotti.DistributoreEntity;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.JoinType;
import org.springframework.data.jpa.domain.Specification;

public class ManutenzioniSpecifications {
    public static Specification<ManutenzioneEntity> hasCliente(String partitaIva){
        return ((root, query, cb) ->
                cb.equal(root.get("cliente"), partitaIva));
    }

    public static Specification<ManutenzioneEntity> hasModello(String modello){
        return (root, query, cb) -> {
            Join<ManutenzioneEntity, DistributoreEntity> joinDistributore = root.join("distributore", JoinType.INNER);
            return cb.like(joinDistributore.get("modello"), "%"+modello+"%");
        };
    }
}
