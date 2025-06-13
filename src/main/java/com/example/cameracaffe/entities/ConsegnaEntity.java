package com.example.cameracaffe.entities;

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
public class ConsegnaEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Date dataDiConsegna;

    @OneToOne(optional = false, cascade = CascadeType.ALL)
    @JoinColumn(name = "ordine")
    private OrdineEntity ordine;

    @OneToMany(mappedBy = "consegna")
    private List<DettaglioConsegnaEntity> dettagliConsegna;

}
