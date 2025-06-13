package com.example.cameracaffe.entities;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Embeddable
@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmbeddedDettaglioOrdineKey {
    private long ordineId;
    private long prodottoId;
}
