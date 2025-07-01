package com.example.cameracaffe.repos.interventi;

import com.example.cameracaffe.entities.interventi.InterventoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface InterventoRepository extends JpaRepository<InterventoEntity, Long> {

}
