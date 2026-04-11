package com.hospital.s.o.s.model.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;
import com.hospital.s.o.s.model.enums.TipoDocumento;

@Data
public class PacienteDTO {
    private Long id;

    @NotNull(message = "El tipo de documento es obligatorio")
    private TipoDocumento tipoDocumento;

    @NotBlank(message = "El documento es obligatorio")
    private String documento;

    @NotBlank(message = "Nombre requerido")
    private String primerNombre;
    private String segundoNombre;

    @NotBlank(message = "Apellido requerido")
    private String primerApellido;
    private String segundoApellido;

    @Email(message = "Email inválido")
    private String correoElectronico;

    @NotNull
    @Min(0)
    private Integer edad;

    @NotNull
    private LocalDate fechaNacimiento;

    @NotBlank
    private String genero;

    @NotBlank
    private String direccionResidencia;

    @NotBlank
    private String eps;
}
