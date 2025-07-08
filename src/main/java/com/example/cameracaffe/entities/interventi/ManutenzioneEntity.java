package com.example.cameracaffe.entities.interventi;

import com.example.cameracaffe.entities.UtilizzoEntity;
import com.example.cameracaffe.entities.richieste.RichiestaManutezioneEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Inheritance(strategy = InheritanceType.JOINED)
public class ManutenzioneEntity extends InterventoEntity {
    @Id
    private long id;

    private String soluzione;

    @OneToMany(mappedBy = "intervento")
    private List<UtilizzoEntity> utilizzi;

    @OneToOne
    @JoinColumn(name = "richiesta_manutenzione")
    private RichiestaManutezioneEntity richiestaManutezione;
}
