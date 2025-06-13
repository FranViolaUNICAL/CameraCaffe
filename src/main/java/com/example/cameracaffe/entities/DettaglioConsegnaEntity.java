package com.example.cameracaffe.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DettaglioConsegnaEntity {

    @EmbeddedId
    private EmbeddedDettaglioConsegnaKey key;

    private int quantita;

    @MapsId("prodottoId")
    @ManyToOne
    @JoinColumn(name="prodottoId")
    private ProdottoEntity prodotto;

    @MapsId("consegnaId")
    @ManyToOne
    @JoinColumn(name="consegnaId")
    private ConsegnaEntity consegna;

}
