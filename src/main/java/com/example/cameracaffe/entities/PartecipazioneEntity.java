package com.example.cameracaffe.entities;

import com.example.cameracaffe.entities.embeddedKeys.EmbeddedPartecipazioneKey;
import com.example.cameracaffe.entities.interventi.InterventoEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PartecipazioneEntity {
    @EmbeddedId
    private EmbeddedPartecipazioneKey id;

    @MapsId("matricola")
    @ManyToOne
    @JoinColumn(name = "matricola")
    private TecnicoEntity tecnico;

    @MapsId("interventoId")
    @ManyToOne
    @JoinColumn(name = "intervento_id")
    private InterventoEntity intervento;
}
