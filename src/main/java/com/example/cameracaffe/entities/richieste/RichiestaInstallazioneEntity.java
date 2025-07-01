package com.example.cameracaffe.entities.richieste;

import com.example.cameracaffe.entities.interventi.InstallazioneEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RichiestaInstallazioneEntity extends RichiestaEntity {
    @Id
    private long id;

    @OneToOne
    @JoinColumn(name = "installazione")
    private InstallazioneEntity installazione;
}
