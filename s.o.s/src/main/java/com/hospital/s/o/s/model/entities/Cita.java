package com.hospital.s.o.s.model.entities;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import com.hospital.s.o.s.model.enums.EstadoCita;
import com.hospital.s.o.s.model.enums.EstadoAsistencia;
import jakarta.persistence.Enumerated;
import jakarta.persistence.EnumType;
import jakarta.persistence.CascadeType;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.Positive;
import lombok.Data;

/**
 * Entidad que representa la programación de una cita médica (Control Total).
 */
@Entity
@Data
@Table(name = "citas")
public class Cita {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Future(message = "La cita no se puede programar en el pasado")
    @Column(nullable = false)
    private LocalDateTime fechaHora;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private EstadoCita estadoCita;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private EstadoAsistencia estadoAsistencia;

    @ManyToOne
    @JoinColumn(name = "medico_id", nullable = false)
    private Medico medico;

    @ManyToOne
    @JoinColumn(name = "paciente_id", nullable = false)
    private Paciente paciente;

    @ManyToOne
    @JoinColumn(name = "consultorio_id", nullable = false)
    private Consultorio consultorio;

    @OneToOne(mappedBy = "cita", cascade = CascadeType.ALL)
    private CancelacionCita registroCancelacion;

    /** Breve descripción de por qué viene el paciente a la consulta. */
    @Column
    private String motivoConsulta;

    /**
     * Costo para manejar el cobro, especialmente si tiene 3 inasistencias
     * acumuladas.
     */
    @Positive(message = "El costo de la cita debe ser un valor positivo")
    @Column
    private Double costoCita;

    /** Campo para el reporte financiero, indica si la cita se pagó. */
    @Column(nullable = false)
    private Boolean pagada = false;

    /** Indica si la cita es de telemedicina (virtual) o presencial. */
    @Column(nullable = false)
    private Boolean telemedicina = false;

    @OneToOne(mappedBy = "cita", cascade = CascadeType.ALL, orphanRemoval = true)
    private Diagnostico diagnostico;

    public Cita() {
    }

    public Cita(Long id, LocalDateTime fechaHora, EstadoCita estadoCita, EstadoAsistencia estadoAsistencia,
            Medico medico, Paciente paciente, Consultorio consultorio, CancelacionCita registroCancelacion,
            String motivoConsulta, Double costoCita, Boolean pagada, Boolean telemedicina) {
        this.id = id;
        this.fechaHora = fechaHora;
        this.estadoCita = estadoCita;
        this.estadoAsistencia = estadoAsistencia;
        this.medico = medico;
        this.paciente = paciente;
        this.consultorio = consultorio;
        this.registroCancelacion = registroCancelacion;
        this.motivoConsulta = motivoConsulta;
        this.costoCita = costoCita;
        this.pagada = pagada;
        this.telemedicina = telemedicina;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getFechaHora() {
        return fechaHora;
    }

    public void setFechaHora(LocalDateTime fechaHora) {
        this.fechaHora = fechaHora;
    }

    public EstadoCita getEstadoCita() {
        return estadoCita;
    }

    public void setEstadoCita(EstadoCita estadoCita) {
        this.estadoCita = estadoCita;
    }

    public EstadoAsistencia getEstadoAsistencia() {
        return estadoAsistencia;
    }

    public void setEstadoAsistencia(EstadoAsistencia estadoAsistencia) {
        this.estadoAsistencia = estadoAsistencia;
    }

    public Medico getMedico() {
        return medico;
    }

    public void setMedico(Medico medico) {
        this.medico = medico;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }

    public Consultorio getConsultorio() {
        return consultorio;
    }

    public void setConsultorio(Consultorio consultorio) {
        this.consultorio = consultorio;
    }

    public CancelacionCita getRegistroCancelacion() {
        return registroCancelacion;
    }

    public void setRegistroCancelacion(CancelacionCita registroCancelacion) {
        this.registroCancelacion = registroCancelacion;
    }

    public String getMotivoConsulta() {
        return motivoConsulta;
    }

    public void setMotivoConsulta(String motivoConsulta) {
        this.motivoConsulta = motivoConsulta;
    }

    public Double getCostoCita() {
        return costoCita;
    }

    public void setCostoCita(Double costoCita) {
        this.costoCita = costoCita;
    }

    public Boolean getPagada() {
        return pagada;
    }

    public void setPagada(Boolean pagada) {
        this.pagada = pagada;
    }

    public Boolean getTelemedicina() {
        return telemedicina;
    }

    public void setTelemedicina(Boolean telemedicina) {
        this.telemedicina = telemedicina;
    }
}
