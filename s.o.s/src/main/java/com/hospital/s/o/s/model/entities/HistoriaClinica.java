package com.hospital.s.o.s.model.entities;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import java.util.List;

/**
 * Entidad principal que agrupa todo el historial médico de un paciente.
 */
@Entity
@Table(name = "historias_clinicas")
public class HistoriaClinica {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "paciente_id", nullable = false, unique = true) // Agregado unique para evitar duplicados
    private Paciente paciente;

    // --- NUEVOS CAMPOS ---
    private String antecedentes;
    private String grupoSanguineo;
    private String estado;
    private java.time.LocalDateTime fechaCreacion;
    // ---------------------

    @OneToMany(mappedBy = "historiaClinica", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Diagnostico> diagnosticos;

    public HistoriaClinica() {
    }

    // Getters y Setters de los nuevos campos (Generarlos en IntelliJ)
    public String getAntecedentes() {
        return antecedentes;
    }

    public void setAntecedentes(String antecedentes) {
        this.antecedentes = antecedentes;
    }

    public String getGrupoSanguineo() {
        return grupoSanguineo;
    }

    public void setGrupoSanguineo(String grupoSanguineo) {
        this.grupoSanguineo = grupoSanguineo;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public java.time.LocalDateTime getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(java.time.LocalDateTime fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    // Mantén tus otros Getters y Setters (id, paciente, diagnosticos)...
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }
}