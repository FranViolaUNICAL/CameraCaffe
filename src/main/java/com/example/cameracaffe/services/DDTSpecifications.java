package com.example.cameracaffe.services;

import com.example.cameracaffe.DTO.TipoDDT;
import com.example.cameracaffe.entities.DDTEntity;
import com.example.cameracaffe.entities.DettaglioDDTEntity;
import com.example.cameracaffe.entities.prodotti.ProdottoEntity;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.JoinType;
import jakarta.persistence.criteria.Root;
import jakarta.persistence.criteria.Subquery;
import org.springframework.data.jpa.domain.Specification;

public class DDTSpecifications {
    public static Specification<DDTEntity> hasID(Long id) {
        return (root, query, cb) -> {
            if(id == null){
                return null;
            }
            return cb.equal(root.get("id"), id);
        };
    }

    public static Specification<DDTEntity> hasFornitore(String partitaIva) {
        return(root, query, cb) -> {
            if(partitaIva == null || partitaIva.isEmpty()){
                return null;
            }
            Join<DDTEntity, DettaglioDDTEntity> dettaglioJoin = root.join("dettagliDDT", JoinType.INNER);
            Join<DettaglioDDTEntity, ProdottoEntity> prodottoJoin = dettaglioJoin.join("prodotto", JoinType.INNER);
            return cb.equal(prodottoJoin.get("fornitore"), partitaIva);
        };
    }

    public static Specification<DDTEntity> hasPesoTotaleMinimo(double pesoMinimo) {
        return (root, query, cb) -> {
            if (pesoMinimo <= 0) {
                return cb.conjunction();
            }

            // Subquery che fa SUM(quantità * peso)
            Subquery<Long> subquery = query.subquery(Long.class);
            Root<DettaglioDDTEntity> dettaglioRoot = subquery.from(DettaglioDDTEntity.class);

            // Join con prodotto per prendere peso unitario
            Join<DettaglioDDTEntity, ProdottoEntity> prodottoJoin = dettaglioRoot.join("prodotto");

            // Collegare dettaglio ➜ DDT corrente
            subquery.select(cb.sum(
                    cb.prod(
                            dettaglioRoot.get("quantita"),
                            prodottoJoin.get("peso")
                    )
            ));
            subquery.where(cb.equal(dettaglioRoot.get("ddt"), root));

            return cb.ge(subquery, pesoMinimo);
        };
    }

    public static Specification<DDTEntity> hasQuantitaTotaleMinima(long minQuantita) {
        return (root, query, cb) -> {
            if (minQuantita <= 0) {
                return cb.conjunction();
            }

            // Subquery con SUM(quantità)
            Subquery<Long> subquery = query.subquery(Long.class);
            Root<DettaglioDDTEntity> dettaglioRoot = subquery.from(DettaglioDDTEntity.class);

            subquery.select(cb.sum(dettaglioRoot.get("quantita")));
            subquery.where(cb.equal(dettaglioRoot.get("ddt"), root));

            return cb.ge(subquery, minQuantita);
        };
    }

    public static Specification<DDTEntity> hasTipoDDT(TipoDDT tipoDDT) {
        return(root, query, cb) -> {
            if(tipoDDT == null){
                return null;
            }
            return cb.equal(root.get("tipoDDT"), tipoDDT);
        };
    }


}
