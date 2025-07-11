package com.example.cameracaffe.entities;

import com.example.cameracaffe.entities.embeddedKeys.EmbeddedDettaglioOrdineKey;
import com.example.cameracaffe.entities.prodotti.ProdottoEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DettaglioOrdineEntity {

    @EmbeddedId
    private EmbeddedDettaglioOrdineKey id;

    @MapsId("prodottoId")
    @ManyToOne
    @JoinColumn(name="prodottoId")
    private ProdottoEntity prodotto;

    @MapsId("ordineId")
    @ManyToOne
    @JoinColumn(name="ordineId")
    private OrdineEntity ordine;

}
