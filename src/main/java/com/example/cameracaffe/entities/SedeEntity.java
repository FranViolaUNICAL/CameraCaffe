package com.example.cameracaffe.entities;

import com.example.cameracaffe.DTO.TipoSede;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SedeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String indirizzo;

    private TipoSede tipo;

    @ManyToOne
    @JoinColumn(name = "fornitore_id")
    private FornitoreEntity fornitore;
}
