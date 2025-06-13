package com.example.cameracaffe.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UtilizzoEntity {
    @EmbeddedId
    private EmbeddedUtilizzoKey id;

    @MapsId("interventoId")
    @ManyToOne
    @JoinColumn(name = "intervento_id")
    private InterventoEntity intervento;

    @MapsId("ricambioId")
    @ManyToOne
    @JoinColumn(name = "ricambio_id")
    private RicambioEntity ricambio;
}
