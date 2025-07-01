package com.example.cameracaffe.repos;

import com.example.cameracaffe.entities.PartecipazioneEntity;
import com.example.cameracaffe.entities.embeddedKeys.EmbeddedPartecipazioneKey;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PartecipazioneRepository extends JpaRepository<PartecipazioneEntity, EmbeddedPartecipazioneKey> {
    List<PartecipazioneEntity> findByTecnico_matricola(long matricola);
    List<PartecipazioneEntity> findByIntervento_id(long intervento);
}
