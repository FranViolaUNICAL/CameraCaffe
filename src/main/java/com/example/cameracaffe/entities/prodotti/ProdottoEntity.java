package com.example.cameracaffe.entities.prodotti;

import com.example.cameracaffe.entities.*;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Inheritance(strategy = InheritanceType.JOINED)
public class ProdottoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String descrizione;
    private int quantita;

    @OneToMany(mappedBy = "prodotto")
    List<MovimentazioneUscitaEntity> movimentazioni;

    @ManyToOne
    @JoinColumn(name = "fornitore_p_iva")
    private FornitoreEntity fornitore;

    @OneToMany(mappedBy = "prodotto")
    private List<DettaglioConsegnaEntity> dettagliConsegna;

    @OneToMany(mappedBy = "prodotto")
    private List<DettaglioOrdineEntity> dettagliOrdine;

    @OneToMany(mappedBy = "prodotto")
    private List<DettaglioDDTEntity> dettagliDDT;
}
