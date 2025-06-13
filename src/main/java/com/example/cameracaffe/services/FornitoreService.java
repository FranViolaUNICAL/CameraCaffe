package com.example.cameracaffe.services;

import com.example.cameracaffe.DTO.TipoDDT;
import com.example.cameracaffe.entities.*;
import com.example.cameracaffe.repos.FornitoreRepository;
import jakarta.persistence.criteria.*;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class FornitoreService {
    private FornitoreRepository fornitoreRepository;

    public FornitoreService(FornitoreRepository fornitoreRepository) {
        this.fornitoreRepository = fornitoreRepository;
    }

    public static Specification<FornitoreEntity> hasSedePosizione(String posizione) {
        return (root, query, cb) -> {
            if (posizione == null) return null;
            return cb.equal(root.join("sedi").get("indirizzo"), posizione);
        };
    }

    public static Specification<FornitoreEntity> hasTipologiaProdotto(String tipo) {
        return (root, query, cb) -> {
            if (tipo == null) return null;
            return cb.equal(root.join("tipologieFornite").get("nome"), tipo);
        };
    }

    public static Specification<FornitoreEntity> ultimoOrdineRichiestoBetween(LocalDate from, LocalDate to) {
        return (root, query, cb) -> {
            if (from == null && to == null) return null;

            Join<FornitoreEntity, OrdineEntity> joinOrdini = root.join("ordini", JoinType.LEFT);
                Expression<LocalDate> dataRichiesta = joinOrdini.get("dataOrdine");

            Predicate predicate = cb.conjunction();
            if (from != null) {
                predicate = cb.and(predicate, cb.greaterThanOrEqualTo(dataRichiesta, from));
            }
            if (to != null) {
                predicate = cb.and(predicate, cb.lessThanOrEqualTo(dataRichiesta, to));
            }

            return predicate;
        };
    }

    public static Specification<FornitoreEntity> hasMinResi(long minResi) {
        return (root, query, cb) -> {
            if (minResi <= 0) return null;

            // Subquery per contare i DDT di tipo DI_RESO
            Subquery<Long> subquery = query.subquery(Long.class);
            Root<DDTEntity> ddtRoot = subquery.from(DDTEntity.class);

            // Join DDT → DettaglioDDT
            Join<DDTEntity, DettaglioDDTEntity> dettaglioJoin = ddtRoot.join("dettagliDDT");
            // Join DettaglioDDT → Prodotto
            Join<DettaglioDDTEntity, ProdottoEntity> prodottoJoin = dettaglioJoin.join("prodotto");
            // Join Prodotto → Fornitore
            Join<ProdottoEntity, FornitoreEntity> fornitoreJoin = prodottoJoin.join("fornitore");

            // Seleziona count
            subquery.select(cb.countDistinct(ddtRoot));
            subquery.where(
                    cb.equal(ddtRoot.get("tipoDDT"), TipoDDT.DI_RESO),
                    cb.equal(fornitoreJoin, root)
            );

            return cb.ge(subquery, minResi);
        };
    }

    public List<FornitoreEntity> findAll(){
        return fornitoreRepository.findAll();
    }

    public void save(FornitoreEntity fornitoreEntity) {
        fornitoreRepository.save(fornitoreEntity);
    }

    public void delete(FornitoreEntity fornitoreEntity){
        fornitoreRepository.delete(fornitoreEntity);
    }

    public List<FornitoreEntity> findFiltered(long minResi, LocalDate from, LocalDate to, String tipo, String posizione){
        Specification<FornitoreEntity> spec = (root, query, cb) -> cb.conjunction();
        spec=spec
                .and(hasSedePosizione(posizione))
                .and(hasTipologiaProdotto(tipo))
                .and(ultimoOrdineRichiestoBetween(from, to))
                .and(hasMinResi(minResi));
        return fornitoreRepository.findAll(spec, Sort.by("partitaIva"));
    }

}
