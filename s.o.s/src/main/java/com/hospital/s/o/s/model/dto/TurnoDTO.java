package com.hospital.s.o.s.model.dto;

import java.time.LocalDate;
import java.time.LocalTime;

import lombok.Data;

@Data
public class TurnoDTO {
    private LocalDate fecha;
    private LocalTime horaInicio; // Validar 8-12 o 2-6. Nixon
    private LocalTime horaFin;
    private Long medicoId;
    private Long id;

    public void setId(Long id) {
        this.id = id;
    }
}
