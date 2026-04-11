package com.hospital.s.o.s.model.dto;

import lombok.Data;

@Data
public class MedicoDTO {
    private String nombre;
    private String apellido;
    private String tarjetaProfesional;
    private String telefonoContacto;
    private Integer aniosExperiencia;
    private Long especialidadId; // Relación con especialidades
    private Boolean activo;
}
