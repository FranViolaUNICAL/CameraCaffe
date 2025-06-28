package com.example.cameracaffe.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ClienteEntity {
    @Id
    private String pIva;
    private String ragioneSociale;
}
