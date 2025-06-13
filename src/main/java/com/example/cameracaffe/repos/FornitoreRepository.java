package com.example.cameracaffe.repos;

import com.example.cameracaffe.entities.FornitoreEntity;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FornitoreRepository extends JpaRepository<FornitoreEntity, String>, JpaSpecificationExecutor<FornitoreEntity> {
        List<FornitoreEntity> findAll(Specification<FornitoreEntity> spec, Sort sort);

}
