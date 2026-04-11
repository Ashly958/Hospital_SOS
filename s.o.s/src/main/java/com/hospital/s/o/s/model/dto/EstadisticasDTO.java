package com.hospital.s.o.s.model.dto;

import lombok.Data;

@Data
public class EstadisticasDTO {
    private long totalPacientes;
    private long totalMedicos;
    private long totalMantenimientos;
    private long totalCancelaciones;
}
