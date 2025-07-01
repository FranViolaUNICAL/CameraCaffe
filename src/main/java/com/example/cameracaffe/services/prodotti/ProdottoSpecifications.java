package com.example.cameracaffe.services.prodotti;

import com.example.cameracaffe.DTO.TipologiaProdotto;
import com.example.cameracaffe.entities.prodotti.AlimentareEntity;
import com.example.cameracaffe.entities.prodotti.DistributoreEntity;
import com.example.cameracaffe.entities.prodotti.ProdottoEntity;
import com.example.cameracaffe.entities.prodotti.RicambioEntity;
import org.springframework.data.jpa.domain.Specification;

public class ProdottoSpecifications {
    public static Specification<ProdottoEntity> hasTipoProdotto(TipologiaProdotto tipo) {
        return (root, query, cb) -> {
            if (tipo == null) return null;

            switch (tipo) {
                case ALIMENTARE:
                    return cb.equal(root.type(), AlimentareEntity.class);
                case DISTRIBUTORE:
                    return cb.equal(root.type(), DistributoreEntity.class);
                case RICAMBIO:
                    return cb.equal(root.type(), RicambioEntity.class);
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
