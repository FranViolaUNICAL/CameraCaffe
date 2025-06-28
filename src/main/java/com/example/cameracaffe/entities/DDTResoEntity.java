package com.example.cameracaffe.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DDTResoEntity {
    @Id
    private long id;

    @MapsId
    @OneToOne
    @JoinColumn(name = "id")
    private DDTEntity ddtEntity;

    private double commissioneTrasporto;
}
