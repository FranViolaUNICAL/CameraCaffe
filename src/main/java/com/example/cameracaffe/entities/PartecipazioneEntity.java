package com.example.cameracaffe.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

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
    private Intervento intervento;
}
