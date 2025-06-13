package com.example.cameracaffe.repos;

import com.example.cameracaffe.entities.ProdottoEntity;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProdottoRepository extends JpaRepository<ProdottoEntity, Long> , JpaSpecificationExecutor<ProdottoEntity> {
    @Query(
            "SELECT p FROM AlimentareEntity a JOIN a.prodotto p"
    )
    public List<ProdottoEntity> findProdottoAlimentare();
    @Query(
            "SELECT p FROM RicambioEntity r JOIN r.prodotto p"
    )
    public List<ProdottoEntity> findProdottoRicambio();
    @Query(
            "SELECT p FROM DistributoreEntity r JOIN r.prodotto p"
    )
    public List<ProdottoEntity> findProdottoDistributore();

    List<ProdottoEntity> findAll(Specification spec);

}
