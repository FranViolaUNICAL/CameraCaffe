package com.example.cameracaffe.entities.embeddedKeys;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Embeddable
@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmbeddedLottoKey implements Serializable {
    private long lottoId;
    private long alimentareId;
}
