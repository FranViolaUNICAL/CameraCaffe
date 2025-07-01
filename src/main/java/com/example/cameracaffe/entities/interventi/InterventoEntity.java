package com.example.cameracaffe.entities.interventi;

import com.example.cameracaffe.entities.ClienteEntity;
import com.example.cameracaffe.entities.PartecipazioneEntity;
import com.example.cameracaffe.entities.prodotti.DistributoreEntity;
import com.example.cameracaffe.entities.richieste.RichiestaEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Inheritance(strategy = InheritanceType.JOINED)
public class InterventoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private Date data;
    private String descrizione;

    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private ClienteEntity cliente;

    @ManyToOne
    @JoinColumn(name = "distributore_id")
    private DistributoreEntity distributore;

    @OneToMany(mappedBy = "intervento")
    private List<PartecipazioneEntity> partecipazioni;
}
