package com.example.cameracaffe.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class InstallazioneEntity {
    @Id
    private long id;

    @OneToOne
    @MapsId
    @JoinColumn(name = "id")
    private InterventoEntity intervento;
}
