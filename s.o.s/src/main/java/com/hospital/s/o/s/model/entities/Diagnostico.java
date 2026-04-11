package com.hospital.s.o.s.model.entities;

import java.time.LocalDateTime;
import jakarta.persistence.*;

@Entity
@Table(name = "diagnosticos")
public class Diagnostico {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String descripcion;

    @Column(nullable = false)
    private LocalDateTime fecha;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "historia_id", nullable = false)
    private HistoriaClinica historiaClinica;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "cita_id", nullable = false)
    private Cita cita;

    public Diagnostico() {
    }

    // Getters y Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public LocalDateTime getFecha() {
        return fecha;
    }

    public void setFecha(LocalDateTime fecha) {
        this.fecha = fecha;
    }

    public HistoriaClinica getHistoriaClinica() {
        return historiaClinica;
    }

    public void setHistoriaClinica(HistoriaClinica historiaClinica) {
        this.historiaClinica = historiaClinica;
    }

    public Cita getCita() {
        return cita;
    }

    public void setCita(Cita cita) {
        this.cita = cita;
    }
}