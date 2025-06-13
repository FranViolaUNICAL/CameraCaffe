package com.example.cameracaffe.repos;

import com.example.cameracaffe.entities.ConsegnaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConsegnaRepository extends JpaRepository<ConsegnaEntity, Long> {

}
