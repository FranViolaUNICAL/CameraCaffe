package com.example.cameracaffe.entities.interventi;

import com.example.cameracaffe.entities.richieste.RichiestaInstallazioneEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Inheritance(strategy = InheritanceType.JOINED)
public class InstallazioneEntity extends InterventoEntity{
    @Id
    private long id;

    @OneToOne
    @JoinColumn(name = "richiesta_installazione")
    private RichiestaInstallazioneEntity richiestaInstallazione;
}
