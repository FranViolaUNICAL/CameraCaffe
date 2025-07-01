package com.example.cameracaffe.repos;

import com.example.cameracaffe.entities.ClienteEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<ClienteEntity, String> {

}
