package com.hospital.s.o.s.model.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Enumerated;
import jakarta.persistence.EnumType;
import com.hospital.s.o.s.model.enums.TipoDocumento;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Positive;

/**
 * Perfil Profesional del médico.
 */
@Entity
@Table(name = "medicos")
public class Medico {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TipoDocumento tipoDocumento;

    /** Documento de identidad del médico. */
    @Column(nullable = false, unique = true, length = 20)
    private String documento;

    /** Edad del médico. */
    @Min(value = 18, message = "El médico debe ser mayor de edad")
    @Column(nullable = false)
    private Integer edad;

    @Column(nullable = false, length = 50)
    private String nombre;

    @Column(nullable = false, length = 50)
    private String apellido;

    @Column(nullable = false, unique = true)
    private String tarjetaProfesional;

    /** Especialidad (Relación con la tabla Especialidad). */
    @ManyToOne
    @JoinColumn(name = "especialidad_id", nullable = false)
    private Especialidad especialidad;

    /** Años de experiencia del profesional. */
    @Positive(message = "Los años de experiencia deben ser positivos")
    @Column(nullable = false)
    private Integer aniosExperiencia;

    /** Teléfono de contacto privado del médico. */
    @Column(nullable = false)
    private String telefonoContacto;

    /** Ruta a una imagen de la firma digital (para uso en fórmulas médicas). */
    @Column
    private String firmaDigital;

    /** Biografía del médico (Para que el paciente lea sobre él en la App). */
    @Column(columnDefinition = "TEXT")
    private String biografia;

    private boolean activo = true;

    // Importante: Esto permite que al borrar el médico se borren sus turnos
    // automáticamente
    @jakarta.persistence.OneToMany(mappedBy = "medico", cascade = jakarta.persistence.CascadeType.ALL, orphanRemoval = true)
    private java.util.List<Turno> turnos;

    public Medico() {
    }

    public Medico(Long id, TipoDocumento tipoDocumento, String documento, Integer edad, String nombre, String apellido,
            String tarjetaProfesional, Especialidad especialidad,
            Integer aniosExperiencia, String telefonoContacto, String firmaDigital, String biografia, boolean activo) {
        this.id = id;
        this.tipoDocumento = tipoDocumento;
        this.documento = documento;
        this.edad = edad;
        this.nombre = nombre;
        this.apellido = apellido;
        this.tarjetaProfesional = tarjetaProfesional;
        this.especialidad = especialidad;
        this.aniosExperiencia = aniosExperiencia;
        this.telefonoContacto = telefonoContacto;
        this.firmaDigital = firmaDigital;
        this.biografia = biografia;
        this.activo = activo;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDocumento() {
        return documento;
    }

    public void setDocumento(String documento) {
        this.documento = documento;
    }

    public Integer getEdad() {
        return edad;
    }

    public void setEdad(Integer edad) {
        this.edad = edad;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public TipoDocumento getTipoDocumento() {
        return tipoDocumento;
    }

    public void setTipoDocumento(TipoDocumento tipoDocumento) {
        this.tipoDocumento = tipoDocumento;
    }

    public String getTarjetaProfesional() {
        return tarjetaProfesional;
    }

    public void setTarjetaProfesional(String tarjetaProfesional) {
        this.tarjetaProfesional = tarjetaProfesional;
    }

    public Especialidad getEspecialidad() {
        return especialidad;
    }

    public void setEspecialidad(Especialidad especialidad) {
        this.especialidad = especialidad;
    }

    public Integer getAniosExperiencia() {
        return aniosExperiencia;
    }

    public void setAniosExperiencia(Integer aniosExperiencia) {
        this.aniosExperiencia = aniosExperiencia;
    }

    public String getTelefonoContacto() {
        return telefonoContacto;
    }

    public void setTelefonoContacto(String telefonoContacto) {
        this.telefonoContacto = telefonoContacto;
    }

    public String getFirmaDigital() {
        return firmaDigital;
    }

    public void setFirmaDigital(String firmaDigital) {
        this.firmaDigital = firmaDigital;
    }

    public String getBiografia() {
        return biografia;
    }

    public void setBiografia(String biografia) {
        this.biografia = biografia;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }
}
