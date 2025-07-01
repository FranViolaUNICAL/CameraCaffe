package com.example.cameracaffe.repos;

import com.example.cameracaffe.entities.FatturaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FatturaRepository extends JpaRepository<FatturaEntity, Long> {

}
