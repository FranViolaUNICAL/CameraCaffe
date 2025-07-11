package com.example.cameracaffe.entities;

import com.example.cameracaffe.entities.embeddedKeys.EmbeddedDettaglioDDTKey;
import com.example.cameracaffe.entities.prodotti.ProdottoEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DettaglioDDTEntity {
    @EmbeddedId
    private EmbeddedDettaglioDDTKey key;

    @MapsId("DDTId")
    @ManyToOne
    @JoinColumn(name = "DDT_id")
    private DDTEntity ddt;

    @MapsId("prodottoId")
    @ManyToOne
    @JoinColumn(name = "prodotto_id")
    private ProdottoEntity prodotto;

    private int quantita;
    private double pesoUnitario;
}
