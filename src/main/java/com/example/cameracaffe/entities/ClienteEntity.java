package com.example.cameracaffe.entities;

import com.example.cameracaffe.entities.interventi.InterventoEntity;
import com.example.cameracaffe.entities.richieste.RichiestaEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class  ClienteEntity {
    @Id
    private String pIva;
    private String ragioneSociale;

    @OneToMany(mappedBy = "cliente")
    private List<InterventoEntity> interventi;

    @OneToMany(mappedBy = "cliente")
    private List<RichiestaEntity> richieste;

    @OneToMany(mappedBy = "cliente")
    private List<SedeEntity> sedi;
}
