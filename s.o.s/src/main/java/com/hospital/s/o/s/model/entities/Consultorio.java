package com.hospital.s.o.s.model.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.util.List;
import jakarta.persistence.CascadeType;

/**
 * Entidad de logística que representa los consultorios físicos.
 */
@Entity
@Table(name = "consultorios")
public class Consultorio {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /** Nombre del consultorio. Ej: Consultorio 101, Sala de Urgencias */
    @Column(nullable = false, unique = true, length = 50)
    private String nombre;

    /** Piso donde se encuentra, para que el paciente no se pierda. */
    @Column(nullable = false)
    private Integer piso;

    /** Indica si posee equipos especiales (Ej: si tiene electrocardiógrafo). */
    @Column(nullable = false)
    private Boolean tieneEquiposEspeciales = false;

    /** Límite de capacidad máxima permitida. */
    @Column(nullable = false)
    private Integer capacidadMaxima;

    @OneToMany(mappedBy = "consultorio", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Cita> citas;

    @OneToMany(mappedBy = "consultorio", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Mantenimiento> mantenimientos;

    public Consultorio() {
    }

    public Consultorio(Long id, String nombre, Integer piso, Boolean tieneEquiposEspeciales, Integer capacidadMaxima) {
        this.id = id;
        this.nombre = nombre;
        this.piso = piso;
        this.tieneEquiposEspeciales = tieneEquiposEspeciales;
        this.capacidadMaxima = capacidadMaxima;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Integer getPiso() {
        return piso;
    }

    public void setPiso(Integer piso) {
        this.piso = piso;
    }

    public Boolean getTieneEquiposEspeciales() {
        return tieneEquiposEspeciales;
    }

    public void setTieneEquiposEspeciales(Boolean tieneEquiposEspeciales) {
        this.tieneEquiposEspeciales = tieneEquiposEspeciales;
    }

    public Integer getCapacidadMaxima() {
        return capacidadMaxima;
    }

    public void setCapacidadMaxima(Integer capacidadMaxima) {
        this.capacidadMaxima = capacidadMaxima;
    }
}
