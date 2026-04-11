package com.hospital.s.o.s.model.dto;

import java.time.LocalDateTime;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import jakarta.validation.constraints.NotNull;

@Data
public class CitaDTO {
    private Long id; // El ID suele ir arriba o abajo, no importa

    @NotNull
    @Future
    private LocalDateTime fechaHora;

    @NotBlank
    private String motivoConsulta;

    @NotNull
    private Long pacienteId;

    @NotNull
    private Long medicoId;

    @NotNull
    private Long consultorioId;

    // ESTOS SON LOS QUE TE FALTABAN Y POR ESO DABA ERROR:
    private String estadoCita;
    private String estadoAsistencia;
    private Double costoCita;
    private Boolean pagada;
    private Boolean telemedicina;

    private java.time.LocalDate fecha;

    // Pega esto al final de tu CitaDTO.java antes de la última }
    public void setEstadoCita(String estadoCita) {
        this.estadoCita = estadoCita;
    }

    public void setEstadoAsistencia(String estadoAsistencia) {
        this.estadoAsistencia = estadoAsistencia;
    }

    public void setCostoCita(Double costoCita) {
        this.costoCita = costoCita;
    }

    public void setPagada(Boolean pagada) {
        this.pagada = pagada;
    }

    public void setTelemedicina(Boolean telemedicina) {
        this.telemedicina = telemedicina;
    }

    public String getEstadoCita() {
        return estadoCita;
    }

    public String getEstadoAsistencia() {
        return estadoAsistencia;
    }

    public Double getCostoCita() {
        return costoCita;
    }

    public Boolean getPagada() {
        return pagada;
    }

    public Boolean getTelemedicina() {
        return telemedicina;
    }
}
