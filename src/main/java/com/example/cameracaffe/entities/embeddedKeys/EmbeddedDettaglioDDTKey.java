package com.example.cameracaffe.entities.embeddedKeys;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Embeddable
@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmbeddedDettaglioDDTKey {
    private long DDTId;
    private long prodottoId;
}
