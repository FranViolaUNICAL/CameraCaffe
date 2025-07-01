package com.example.cameracaffe.entities;

import com.example.cameracaffe.DTO.TipoUtilizzo;
import com.example.cameracaffe.entities.embeddedKeys.EmbeddedUtilizzoKey;
import com.example.cameracaffe.entities.interventi.InterventoEntity;
import com.example.cameracaffe.entities.prodotti.RicambioEntity;
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

    @MapsId("manutenzioneId")
    @ManyToOne
    @JoinColumn(name = "intervento_id")
    private InterventoEntity intervento;

    @MapsId("ricambioId")
    @ManyToOne
    @JoinColumn(name = "ricambio_id")
    private RicambioEntity ricambio;

    private TipoUtilizzo tipoUtilizzo;
}
