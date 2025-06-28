package com.example.cameracaffe.entities;

import com.example.cameracaffe.DTO.NaturaMovimentazione;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MovimentazioneUscitaEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private NaturaMovimentazione naturaMovimentazione;

    private int quantita;

    private Date dataDiMovimentazione;
}
