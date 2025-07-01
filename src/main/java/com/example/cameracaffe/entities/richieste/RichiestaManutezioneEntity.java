package com.example.cameracaffe.entities.richieste;

import com.example.cameracaffe.entities.interventi.ManutenzioneEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RichiestaManutezioneEntity extends RichiestaEntity {
    @Id
    private long id;

    @OneToOne
    @JoinColumn(name = "manutenzione")
    private ManutenzioneEntity manutenzione;
}
