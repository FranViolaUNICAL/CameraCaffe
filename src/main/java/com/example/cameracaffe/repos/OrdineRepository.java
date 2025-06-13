package com.example.cameracaffe.repos;

import com.example.cameracaffe.DTO.StatoOrdine;
import com.example.cameracaffe.entities.OrdineEntity;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrdineRepository extends JpaRepository<OrdineEntity, Long>, JpaSpecificationExecutor<OrdineEntity> {
    List<OrdineEntity> findByStatoOrdine(StatoOrdine statoOrdine);

    @Query(
            "SELECT o " +
                    "FROM OrdineEntity o " +
                    "JOIN o.dettagliOrdine do " +
                    "JOIN do.prodotto p " +
                    "JOIN p.fornitore f " +
                    "WHERE f.partitaIva = :partitaIva"
    )
    List<OrdineEntity> findByFornitorePartitaIva(@Param("partitaIva") String partitaIva);

    List<OrdineEntity> findAll(Specification<OrdineEntity> spec);

}
