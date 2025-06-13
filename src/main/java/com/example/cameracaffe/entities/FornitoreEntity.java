package com.example.cameracaffe.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class FornitoreEntity implements Serializable {

    @Id
    private String partitaIva;

    private String nomeReferente, cognomeReferente, email;

    @OneToMany(mappedBy = "fornitore", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<SedeEntity> sedi;

    @OneToMany(mappedBy = "fornitore")
    private List<ProdottoEntity> prodotti;
}
