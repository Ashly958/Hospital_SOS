package com.hospital.s.o.s.model.dto;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class DiagnosticoDTO {
    private Long id;
    private String descripcion;
    private LocalDateTime fecha;
    private Long historiaId;
    private Long citaId;
}