package com.example.cameracaffe.entities.prodotti;

import com.example.cameracaffe.entities.interventi.InterventoEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DistributoreEntity extends ProdottoEntity {
    @Id
    private Long id;

    private String modello;

    @OneToMany(mappedBy = "distributore")
    private List<InterventoEntity> interventi;
}
