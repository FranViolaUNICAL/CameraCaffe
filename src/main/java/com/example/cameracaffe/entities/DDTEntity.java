package com.example.cameracaffe.entities;

import com.example.cameracaffe.DTO.TipoDDT;
import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DDTEntity {
    @Id
    private long id;
    private Date dataDiEmissione;

    @OneToOne(optional = true)
    @JoinColumn(name = "fattura")
    private FatturaEntity fattura;

    @Nullable
    private double commissione;

    private TipoDDT tipoDDT;

    @OneToMany(mappedBy = "ddt")
    private List<DettaglioDDTEntity> dettagliDDT;
}
