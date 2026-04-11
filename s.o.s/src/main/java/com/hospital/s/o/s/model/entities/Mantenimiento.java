package com.hospital.s.o.s.model.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDateTime;
import com.hospital.s.o.s.model.enums.TipoDocumento;

/**
 * Entidad que registra los mantenimientos realizados en los consultorios.
 */
@Entity
@Table(name = "mantenimientos")
public class Mantenimiento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /** Descripción del trabajo. Ej: "Arreglo de gotera", "Mantenimiento aire". */
    @NotNull(message = "La descripción es obligatoria")
    @Column(nullable = false)
    private String descripcion;

    /** Fecha y hora de inicio del mantenimiento. */
    @NotNull
    @Column(nullable = false)
    private LocalDateTime fechaInicio;

    /** Fecha y hora de finalización del mantenimiento. */
    @NotNull
    @Column(nullable = false)
    private LocalDateTime fechaFin;

    /** Consultorio al cual se le realizó el mantenimiento. */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "consultorio_id", nullable = false)
    private Consultorio consultorio;

    @Column(name = "nombre_tecnico", nullable = false, length = 50)
    private String nombreTecnico;

    @Column(name = "apellido_tecnico", nullable = false, length = 50)
    private String apellidoTecnico;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_documento_tecnico", nullable = false)
    private TipoDocumento tipoDocumentoTecnico;

    /**
     * Documento de identidad del técnico. No es unique porque pueden haber varios
     * mantenimientos del mismo tecnico.
     */
    @Column(name = "documento_tecnico", nullable = false, length = 20)
    private String documentoTecnico;

    public Mantenimiento() {
    }

    public Mantenimiento(Long id, String descripcion, LocalDateTime fechaInicio, LocalDateTime fechaFin,
            Consultorio consultorio, String nombreTecnico, String apellidoTecnico,
            TipoDocumento tipoDocumentoTecnico, String documentoTecnico) {
        this.id = id;
        this.descripcion = descripcion;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.consultorio = consultorio;
        this.nombreTecnico = nombreTecnico;
        this.apellidoTecnico = apellidoTecnico;
        this.tipoDocumentoTecnico = tipoDocumentoTecnico;
        this.documentoTecnico = documentoTecnico;
    }

    // Getters y Setters (Esenciales para que funcione sin Lombok)
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

    public LocalDateTime getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(LocalDateTime fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public LocalDateTime getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(LocalDateTime fechaFin) {
        this.fechaFin = fechaFin;
    }

    public Consultorio getConsultorio() {
        return consultorio;
    }

    public void setConsultorio(Consultorio consultorio) {
        this.consultorio = consultorio;
    }

    public String getNombreTecnico() {
        return nombreTecnico;
    }

    public void setNombreTecnico(String nombreTecnico) {
        this.nombreTecnico = nombreTecnico;
    }

    public String getApellidoTecnico() {
        return apellidoTecnico;
    }

    public void setApellidoTecnico(String apellidoTecnico) {
        this.apellidoTecnico = apellidoTecnico;
    }

    public TipoDocumento getTipoDocumentoTecnico() {
        return tipoDocumentoTecnico;
    }

    public void setTipoDocumentoTecnico(TipoDocumento tipoDocumentoTecnico) {
        this.tipoDocumentoTecnico = tipoDocumentoTecnico;
    }

    public String getDocumentoTecnico() {
        return documentoTecnico;
    }

    public void setDocumentoTecnico(String documentoTecnico) {
        this.documentoTecnico = documentoTecnico;
    }
}
