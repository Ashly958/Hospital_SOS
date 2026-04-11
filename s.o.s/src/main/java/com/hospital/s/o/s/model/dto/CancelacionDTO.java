package com.hospital.s.o.s.model.dto;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class CancelacionDTO {
    private Long id;
    private LocalDateTime fechaCancelacion;
    private String motivo;
    private Long citaId;
}
