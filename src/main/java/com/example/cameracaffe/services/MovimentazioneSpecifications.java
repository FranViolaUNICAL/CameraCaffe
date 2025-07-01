package com.example.cameracaffe.services;

import com.example.cameracaffe.DTO.TipologiaProdotto;
import com.example.cameracaffe.entities.*;
import com.example.cameracaffe.entities.prodotti.AlimentareEntity;
import com.example.cameracaffe.entities.prodotti.DistributoreEntity;
import com.example.cameracaffe.entities.prodotti.ProdottoEntity;
import com.example.cameracaffe.entities.prodotti.RicambioEntity;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.JoinType;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDate;

public class MovimentazioneSpecifications {
    public static Specification<MovimentazioneUscitaEntity> hasFornitore(String fornitore){
        return (root, query, cb) -> {
            if(fornitore == null || fornitore.isEmpty()){
                return null;
            }
            Join<MovimentazioneUscitaEntity, ProdottoEntity> joinProdotto = root.join("prodotto", JoinType.INNER);
            Join<ProdottoEntity, FornitoreEntity> joinFornitore = joinProdotto.join("fornitore", JoinType.INNER);
            return cb.equal(joinFornitore.get("partitaIva"), fornitore);
        };
    }

    public static Specification<MovimentazioneUscitaEntity> hasTipologiaProdotto(TipologiaProdotto tipologiaProdotto) {
        return (root, query, cb) -> {
            if (tipologiaProdotto == null) {
                return null; // Nessun filtro
            }

            Join<MovimentazioneUscitaEntity, ProdottoEntity> joinProdotto = root.join("prodotto", JoinType.INNER);

            return switch (tipologiaProdotto) {
                case ALIMENTARE -> {
                    Join<ProdottoEntity, AlimentareEntity> joinAlimentare = joinProdotto.join("alimentare", JoinType.LEFT);
                    yield cb.isNotNull(joinAlimentare.get("id"));
                }
                case RICAMBIO -> {
                    Join<ProdottoEntity, RicambioEntity> joinRicambio = joinProdotto.join("ricambio", JoinType.LEFT);
                    yield cb.isNotNull(joinRicambio.get("id"));
                }
                case DISTRIBUTORE -> {
                    Join<ProdottoEntity, DistributoreEntity> joinDistributore = joinProdotto.join("distributore", JoinType.LEFT);
                    yield cb.isNotNull(joinDistributore.get("id"));
                }
            };
        };
    }

    public static Specification<MovimentazioneUscitaEntity> dataMovimentazioneBetween(LocalDate from, LocalDate to) {
        return (root, query, cb) -> {
            if (from == null && to == null) return null;
            if (from != null && to != null) return cb.between(root.get("dataDiMovimentazione"), from, to);
            if (from != null) return cb.greaterThanOrEqualTo(root.get("dataDiMovimentazione"), from);
            return cb.lessThanOrEqualTo(root.get("dataDiMovimentazione"), to);
        };
    }

}
