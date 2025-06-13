package com.example.cameracaffe.services;

import com.example.cameracaffe.DTO.StatoOrdine;
import com.example.cameracaffe.DTO.TipologiaProdotto;
import com.example.cameracaffe.entities.DettaglioOrdineEntity;
import com.example.cameracaffe.entities.FornitoreEntity;
import com.example.cameracaffe.entities.OrdineEntity;
import com.example.cameracaffe.entities.ProdottoEntity;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.JoinType;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDate;

public class OrdineSpecifications {

    public static Specification<OrdineEntity> hasTipoProdotto(TipologiaProdotto tipo) {
        return (root, query, cb) -> {
            if (tipo == null) {
                return null;
            }

            // Join ordine ➜ dettagli ➜ prodotto
            Join<OrdineEntity, DettaglioOrdineEntity> dettaglioJoin = root.join("dettagli", JoinType.INNER);
            Join<DettaglioOrdineEntity, ProdottoEntity> prodottoJoin = dettaglioJoin.join("prodotto", JoinType.INNER);

            switch (tipo) {
                case ALIMENTARE:
                    Join<Object, Object> alimentareJoin = prodottoJoin.join("alimentare", JoinType.LEFT);
                    return cb.isNotNull(alimentareJoin.get("id"));
                case DISTRIBUTORE:
                    Join<Object, Object> distributoreJoin = prodottoJoin.join("distributore", JoinType.LEFT);
                    return cb.isNotNull(distributoreJoin.get("id"));
                case RICAMBIO:
                    Join<Object, Object> ricambioJoin = prodottoJoin.join("ricambio", JoinType.LEFT);
                    return cb.isNotNull(ricambioJoin.get("id"));
                default:
                    return null;
            }
        };
    }


    public static Specification<OrdineEntity> dataOrdineBetween(LocalDate from, LocalDate to) {
        return (root, query, cb) -> {
            if (from == null && to == null) return null;
            if (from != null && to != null) return cb.between(root.get("dataOrdine"), from, to);
            if (from != null) return cb.greaterThanOrEqualTo(root.get("dataOrdine"), from);
            return cb.lessThanOrEqualTo(root.get("dataOrdine"), to);
        };
    }

    public static Specification<OrdineEntity> hasNomeFornitore(String partitaIva) {
        return (root, query, cb) -> {
            if (partitaIva == null || partitaIva.isBlank()) return null;

            Join<OrdineEntity, DettaglioOrdineEntity> dettaglioOrdineEntityJoin = root.join("dettagliOrdine", JoinType.INNER);
            Join<DettaglioOrdineEntity, ProdottoEntity> prodottoEntityJoin = dettaglioOrdineEntityJoin.join("prodotto", JoinType.INNER);
            Join<ProdottoEntity, FornitoreEntity> fornitoreJoin = prodottoEntityJoin.join("fornitore", JoinType.INNER);
            return cb.like(cb.lower(fornitoreJoin.get("partitaIva")), "%" + partitaIva.toLowerCase() + "%");
        };
    }

    public static Specification<OrdineEntity> hasStato(StatoOrdine stato) {
        return (root, query, cb) -> {
            if (stato == null) return null;
            return cb.equal(root.get("statoOrdine"), stato);
        };
    }
}

