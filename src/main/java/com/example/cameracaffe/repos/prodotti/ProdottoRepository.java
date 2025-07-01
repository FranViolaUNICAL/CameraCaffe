package com.example.cameracaffe.repos.prodotti;

import com.example.cameracaffe.entities.prodotti.ProdottoEntity;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProdottoRepository extends JpaRepository<ProdottoEntity, Long> , JpaSpecificationExecutor<ProdottoEntity> {
    List<ProdottoEntity> findAll(Specification spec);

}
