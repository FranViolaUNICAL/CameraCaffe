package com.example.cameracaffe.entities.prodotti;

import com.example.cameracaffe.entities.UtilizzoEntity;
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
public class RicambioEntity extends ProdottoEntity {
    @Id
    private Long id;

    @OneToMany(mappedBy = "ricambio")
    private List<UtilizzoEntity> utilizzi;
}
