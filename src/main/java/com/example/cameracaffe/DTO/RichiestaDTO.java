package com.example.cameracaffe.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RichiestaDTO {
    private String descrizione, luogo;
    private Date data;
    private TipoIntervento tipoIntervento;
    private ClienteDTO cliente;
}
