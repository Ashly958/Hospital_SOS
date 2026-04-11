package com.hospital.s.o.s.model.entities;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

/**
 * Entidad que representa la cancelación de una cita médica.
 */
@Entity
@Table(name = "cancelaciones")
public class CancelacionCita {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /** Motivo por el cual se canceló la cita. */
    @Column(nullable = false)
    private String motivo;

    /** Fecha y hora exacta de la cancelación. */
    @Column(nullable = false)
    private LocalDateTime fechaCancelacion;

    /** Relación a la cita original que fue cancelada. */
    @OneToOne
    @JoinColumn(name = "cita_id", nullable = false)
    private Cita cita;

    public CancelacionCita() {
    }

    public CancelacionCita(Long id, String motivo, LocalDateTime fechaCancelacion, Cita cita) {
        this.id = id;
        this.motivo = motivo;
        this.fechaCancelacion = fechaCancelacion;
        this.cita = cita;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMotivo() {
        return motivo;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }

    public LocalDateTime getFechaCancelacion() {
        return fechaCancelacion;
    }

    public void setFechaCancelacion(LocalDateTime fechaCancelacion) {
        this.fechaCancelacion = fechaCancelacion;
    }

    public Cita getCita() {
        return cita;
    }

    public void setCita(Cita cita) {
        this.cita = cita;
    }
}
