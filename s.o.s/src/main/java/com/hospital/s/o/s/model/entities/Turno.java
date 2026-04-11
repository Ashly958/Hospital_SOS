package com.hospital.s.o.s.model.entities;

import java.time.LocalDate;
import java.time.LocalTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

/**
 * Entidad que define la asignación de turnos laborales para los médicos.
 */
@Entity
@Table(name = "turnos")
public class Turno {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /** Fecha asignada para este turno. */
    @Column(nullable = false)
    private LocalDate fecha;

    /** Hora de inicio del turno. (Validaremos que sea 08:00 o 14:00). */
    @Column(nullable = false)
    private LocalTime horaInicio;

    /** Hora de finalización del turno. (Validaremos que sea 12:00 o 18:00). */
    @Column(nullable = false)
    private LocalTime horaFin;

    /** Médico asignado a este turno. */
    @ManyToOne
    @JoinColumn(name = "medico_id", nullable = false)
    private Medico medico;

    public Turno() {
    }

    public Turno(Long id, LocalDate fecha, LocalTime horaInicio, LocalTime horaFin, Medico medico) {
        this.id = id;
        this.fecha = fecha;
        this.horaInicio = horaInicio;
        this.horaFin = horaFin;
        this.medico = medico;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public LocalTime getHoraInicio() {
        return horaInicio;
    }

    public void setHoraInicio(LocalTime horaInicio) {
        this.horaInicio = horaInicio;
    }

    public LocalTime getHoraFin() {
        return horaFin;
    }

    public void setHoraFin(LocalTime horaFin) {
        this.horaFin = horaFin;
    }

    public Medico getMedico() {
        return medico;
    }

    public void setMedico(Medico medico) {
        this.medico = medico;
    }
}
