package com.example.cameracaffe.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AlimentareEntity {
    @Id
    private long id;

    @MapsId
    @OneToOne
    @JoinColumn(name = "id")
    private ProdottoEntity prodotto;

    @OneToMany(mappedBy="alimentare")
    private List<LottoEntity> lotti;
}
