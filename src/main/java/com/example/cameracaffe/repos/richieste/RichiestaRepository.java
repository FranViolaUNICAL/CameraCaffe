package com.example.cameracaffe.repos.richieste;

import com.example.cameracaffe.entities.richieste.RichiestaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RichiestaRepository extends JpaRepository<RichiestaEntity, Long> {
}
