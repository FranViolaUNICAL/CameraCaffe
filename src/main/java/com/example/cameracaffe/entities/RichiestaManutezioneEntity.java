package com.example.cameracaffe.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RichiestaManutezioneEntity {
    @Id
    private long id;

    @MapsId
    @OneToOne
    @JoinColumn(name = "id")
    private RichiestaEntity richiesta;
}
