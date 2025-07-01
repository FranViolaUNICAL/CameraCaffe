package com.example.cameracaffe.entities.prodotti;

import com.example.cameracaffe.entities.LottoEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AlimentareEntity extends ProdottoEntity {
    @Id
    private Long id;

    @OneToMany(mappedBy="alimentare")
    private List<LottoEntity> lotti;
}
