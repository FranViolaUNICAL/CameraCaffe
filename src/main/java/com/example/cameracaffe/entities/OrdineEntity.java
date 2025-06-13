package com.example.cameracaffe.entities;

import com.example.cameracaffe.DTO.StatoOrdine;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrdineEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private StatoOrdine statoOrdine;

    private Date dataOrdine;

    @OneToOne(mappedBy= "ordine", optional = true)
    private ConsegnaEntity consegna;

    @OneToMany(mappedBy = "ordine", cascade = CascadeType.ALL, orphanRemoval = true)
        private List<FatturaEntity> fatture;

    @OneToMany(mappedBy = "ordine")
    private List<DettaglioOrdineEntity> dettagliOrdine;

}
