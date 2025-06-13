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
public class RichiestaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String luogo;
    private String descrizione;
    private Date data;

    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private ClienteEntity cliente;
}
