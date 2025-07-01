package com.example.cameracaffe.entities.embeddedKeys;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Embeddable
@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmbeddedUtilizzoKey {
    private long manutenzioneId;
    private long ricambioId;
}
