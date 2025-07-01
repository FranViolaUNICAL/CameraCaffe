package com.example.cameracaffe.services.interventi;

import com.example.cameracaffe.entities.ClienteEntity;
import com.example.cameracaffe.entities.SedeEntity;
import com.example.cameracaffe.entities.interventi.InstallazioneEntity;
import com.example.cameracaffe.entities.prodotti.DistributoreEntity;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.JoinType;
import org.springframework.data.jpa.domain.Specification;

public class InstallazioneSpecifications {
    public static Specification<InstallazioneEntity> hasCliente(Long clienteId) {
        return(root, query, cb) ->
                cb.equal(root.get("cliente"), clienteId);
    }

    public static Specification<InstallazioneEntity> hasSede(Long sedeId) {
        return(root, query, cb) -> {
            Join<InstallazioneEntity, ClienteEntity> clienteJoin = root.join("cliente", JoinType.INNER);
            Join<ClienteEntity, SedeEntity> sedeJoin = clienteJoin.join("sedi", JoinType.INNER);
            return cb.equal(sedeJoin.get("id"), sedeId);
        };
    }

    public static Specification<InstallazioneEntity> hasModello(String modello) {
        return(root, query, cb) -> {
            Join<InstallazioneEntity, DistributoreEntity> distributoreJoin = root.join("distributore", JoinType.INNER);
            return cb.like(distributoreJoin.get("modello"), "%" + modello + "%");
        };
    }
}
