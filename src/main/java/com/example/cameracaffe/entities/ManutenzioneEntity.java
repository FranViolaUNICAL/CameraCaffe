package com.example.cameracaffe.entities;

import jakarta.persistence.*;
import jdk.jshell.execution.Util;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ManutenzioneEntity {
    @Id
    private long id;

    @OneToOne
    @MapsId
    @JoinColumn(name = "id")
    private InterventoEntity intervento;

    private String soluzione;

    @OneToMany(mappedBy = "manutenzione")
    private List<UtilizzoEntity> utilizzi;
}
