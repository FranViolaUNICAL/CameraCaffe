package com.example.cameracaffe.repos.richieste;

import com.example.cameracaffe.entities.richieste.RichiestaManutezioneEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RichiestaManutenzioneRepository extends JpaRepository<RichiestaManutezioneEntity, Long> {
    @Query(
            "SELECT rm FROM RichiestaManutezioneEntity rm WHERE rm NOT IN (SELECT man.richiestaManutezione FROM ManutenzioneEntity man)"
    )
    List<RichiestaManutezioneEntity> findRichiesteManutenzioneNonGestite();

    List<RichiestaManutezioneEntity> findRichiesteByCliente_pIva(String partitaIva);
}
