package com.hospital.s.o.s.model.dto;

import lombok.Data;

@Data
public class ConsultorioDTO {
    private Long id;
    private String nombre;
    private Integer piso;
    private Boolean tieneEquiposEspeciales;
    private Integer capacidadMaxima;
}
