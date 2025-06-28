package com.example.cameracaffe.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class LottoEntity implements Serializable {
    @EmbeddedId
    private EmbeddedLottoKey key;

    private Date dataScadenza;

    @MapsId("prodottoId")
    @ManyToOne
    @JoinColumn(name="prodottoId")
    private ProdottoEntity prodotto;
}
