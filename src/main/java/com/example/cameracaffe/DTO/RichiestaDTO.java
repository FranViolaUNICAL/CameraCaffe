package com.example.cameracaffe.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RichiestaDTO {
    private long id;
    private String descrizione, luogo;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date data = new Date();
    private TipoIntervento tipoIntervento;
    private ClienteDTO cliente;
    private long idDistributore;
}
