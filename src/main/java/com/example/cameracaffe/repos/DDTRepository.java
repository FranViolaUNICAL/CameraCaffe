package com.example.cameracaffe.repos;

import com.example.cameracaffe.DTO.TipoDDT;
import com.example.cameracaffe.entities.DDTEntity;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DDTRepository extends JpaRepository<DDTEntity, Long>, JpaSpecificationExecutor<DDTEntity> {
    public List<DDTEntity> findAll(Specification<DDTEntity> spec, Sort sort);

}
