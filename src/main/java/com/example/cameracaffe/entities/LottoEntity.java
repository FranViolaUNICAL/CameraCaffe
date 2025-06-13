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
    //Questa classe può essere utilizzata per fare roba come TUTTI GLI ALIMENTI LOTTO N. 18302 SONO SCADUTI
    @EmbeddedId
    private EmbeddedLottoKey key;

    private Date dataScadenza;

    @MapsId("alimentareId")
    @ManyToOne
    @JoinColumn(name="prodotto_id")
    private AlimentareEntity alimentare;
}
