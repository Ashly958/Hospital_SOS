package com.hospital.s.o.s.model.dto;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class HistoriaClinicaDTO {
    private Long id;
    private Long pacienteId;
    private LocalDateTime fechaCreacion;
    private String antecedentes;
    private String grupoSanguineo;
    private String estado; // Ejemplo: "ACTIVA", "INACTIVA"
}