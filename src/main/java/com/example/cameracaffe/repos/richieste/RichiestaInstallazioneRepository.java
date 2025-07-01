package com.example.cameracaffe.repos.richieste;

import com.example.cameracaffe.entities.richieste.RichiestaInstallazioneEntity;
import com.example.cameracaffe.entities.richieste.RichiestaManutezioneEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RichiestaInstallazioneRepository extends JpaRepository<RichiestaInstallazioneEntity, Long> {
    @Query(
            "SELECT ri FROM RichiestaInstallazioneEntity ri WHERE ri NOT IN (SELECT inst.richiestaInstallazione FROM InstallazioneEntity inst)"
    )
    List<RichiestaInstallazioneEntity> findRichiesteInstallazioneNonGestite();

    List<RichiestaInstallazioneEntity> findRichiesteByCliente_pIva(String partitaIva);
}
