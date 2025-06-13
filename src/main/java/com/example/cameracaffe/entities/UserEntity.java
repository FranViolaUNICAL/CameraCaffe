package com.example.cameracaffe.entities;

import com.example.cameracaffe.DTO.Roles;
import jakarta.annotation.Nullable;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String email;
    private String password;

    // Roles é una enum che assume valori ADMIN, EMPLOYEE, CUSTOMER, SUPPLIER
    private Roles role;

    @Nullable
    private String partitaIva;
}
