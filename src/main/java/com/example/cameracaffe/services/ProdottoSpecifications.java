package com.example.cameracaffe.services;

import com.example.cameracaffe.DTO.TipologiaProdotto;
import com.example.cameracaffe.entities.DettaglioOrdineEntity;
import com.example.cameracaffe.entities.OrdineEntity;
import com.example.cameracaffe.entities.ProdottoEntity;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.JoinType;
import org.springframework.data.jpa.domain.Specification;

public class ProdottoSpecifications {
    public static Specification<ProdottoEntity> hasTipoProdotto(TipologiaProdotto tipo) {
        return (root, query, cb) -> {
            if (tipo == null) {
                return null;
            }

            switch (tipo) {
                case ALIMENTARE:
                    Join<Object, Object> alimentareJoin = root.join("alimentare", JoinType.LEFT);
                    return cb.isNotNull(alimentareJoin.get("id"));
                case DISTRIBUTORE:
                    Join<Object, Object> distributoreJoin = root.join("distributore", JoinType.LEFT);
                    return cb.isNotNull(distributoreJoin.get("id"));
                case RICAMBIO:
                    Join<Object, Object> ricambioJoin = root.join("ricambio", JoinType.LEFT);
                    return cb.isNotNull(ricambioJoin.get("id"));
                default:
                    return null;
            }
        };
    }

    public static Specification<ProdottoEntity> isAvailable() {
        return(root, query, cb) -> cb.greaterThan(root.get("quantita"),0);
    }

    public static Specification<ProdottoEntity> isNotAvailable() {
        return(root, query, cb) -> cb.equal(root.get("quantita"), 0);
    }
}
