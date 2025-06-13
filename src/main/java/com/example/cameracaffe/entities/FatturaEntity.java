package com.example.cameracaffe.entities;

import com.example.cameracaffe.DTO.StatoFattura;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class FatturaEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private StatoFattura statoFattura;

    private Date dataEmissione;

    @ManyToOne(optional = false)
    @JoinColumn(name = "ordine")
    private OrdineEntity ordine;

    @OneToOne(optional = false)
    @JoinColumn(name = "ddt")
    private DDTEntity ddt;

}
