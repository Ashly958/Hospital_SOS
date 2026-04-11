package com.hospital.s.o.s.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

public class ReporteDTO {

    @Data
    @AllArgsConstructor
    public class ReporteMorosoDTO {
        private String documento;
        private String nombreCompleto;
        private String correo;
        private Long cantidadInasistencias; // Dato calculado que no está en la entidad Paciente
    }

}
