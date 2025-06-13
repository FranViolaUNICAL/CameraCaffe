package com.example.cameracaffe.entities;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Embeddable
@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmbeddedDettaglioConsegnaKey {
    private long prodottoId;
    private long consegnaId;
}
