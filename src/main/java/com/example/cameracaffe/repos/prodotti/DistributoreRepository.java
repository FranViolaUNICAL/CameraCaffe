package com.example.cameracaffe.repos.prodotti;

import com.example.cameracaffe.entities.prodotti.DistributoreEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DistributoreRepository extends JpaRepository<DistributoreEntity, Long> {

}
