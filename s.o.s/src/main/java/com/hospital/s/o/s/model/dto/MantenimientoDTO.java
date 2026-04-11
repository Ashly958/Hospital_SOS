package com.hospital.s.o.s.model.dto;

import java.time.LocalDateTime;

import lombok.Data;

import com.hospital.s.o.s.model.enums.TipoDocumento;

@Data
public class MantenimientoDTO {
    private String nombreTecnico;
    private String apellidoTecnico;
    private TipoDocumento tipoDocumentoTecnico;
    private String documentoTecnico;

    private String descripcion;
    private LocalDateTime fechaInicio;
    private LocalDateTime fechaFin;
    private Long consultorioId;
    private Long id;

    public void setId(Long id) {
        this.id = id;
    }
}