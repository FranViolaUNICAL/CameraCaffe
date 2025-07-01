package com.example.cameracaffe.repos.prodotti;

import com.example.cameracaffe.entities.prodotti.AlimentareEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AlimentareRepository extends JpaRepository<AlimentareEntity, Long> {

}
