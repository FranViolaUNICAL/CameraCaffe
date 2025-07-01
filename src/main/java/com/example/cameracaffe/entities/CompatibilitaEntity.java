package com.example.cameracaffe.entities;

import com.example.cameracaffe.entities.embeddedKeys.EmbeddedCompatibilitaKey;
import com.example.cameracaffe.entities.prodotti.DistributoreEntity;
import com.example.cameracaffe.entities.prodotti.RicambioEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CompatibilitaEntity {
    @EmbeddedId
    private EmbeddedCompatibilitaKey id;

    @MapsId("ricambioId")
    @ManyToOne
    @JoinColumn(name = "ricambio_id")
    private RicambioEntity ricambio;

    @MapsId("distributoreId")
    @ManyToOne
    @JoinColumn(name = "distributore_id")
    private DistributoreEntity distributore;
}
