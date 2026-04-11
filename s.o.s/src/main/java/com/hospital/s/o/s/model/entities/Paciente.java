package com.hospital.s.o.s.model.entities;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import com.hospital.s.o.s.model.enums.TipoDocumento;
import jakarta.persistence.Enumerated;
import jakarta.persistence.EnumType;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Past;

/**
 * Entidad que representa a un paciente en el sistema (Más que un nombre).
 */
@Entity
@Table(name = "pacientes")
public class Paciente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TipoDocumento tipoDocumento;

    /** Documento de identidad del paciente. */
    @Column(nullable = false, unique = true, length = 20)
    private String documento;

    /** Edad del paciente. */
    @Column(nullable = false)
    private Integer edad;

    /** Primer nombre. Separar nombres ayuda a reportes. */
    @Column(nullable = false, length = 50)
    private String primerNombre;

    /** Segundo nombre. Separar nombres ayuda a reportes. */
    @Column(length = 50)
    private String segundoNombre;

    /** Primer apellido. Fundamental para historias clínicas. */
    @Column(nullable = false, length = 50)
    private String primerApellido;

    /** Segundo apellido. Fundamental para historias clínicas. */
    @Column(length = 50)
    private String segundoApellido;

    /** Fecha de nacimiento (para calcular la edad y saber si es pediatría). */
    @Past(message = "La fecha de nacimiento debe estar en el pasado")
    @Column(nullable = false)
    private LocalDate fechaNacimiento;

    /** Género (Ej: M, F, Otro). */
    @Column(nullable = false)
    private String genero;

    /** Correo electrónico. Para enviar recordatorios de cita. */
    @Email(message = "Debe ser un correo electrónico válido")
    @Column(nullable = false)
    private String correoElectronico;

    /** Dirección de residencia. Por si se requiere visita domiciliaria. */
    @Column(nullable = false)
    private String direccionResidencia;

    /** EPS a la que pertenece el paciente. */
    @Column(nullable = false)
    private String eps;

    /**
     * Indica si el paciente requiere un cobro adelantado (Para la lógica de
     * inasistencias).
     */
    @Column(nullable = false)
    private boolean requierePagoAdelantado = false;

    // Importante importar estas clases de jakarta.persistence
    @jakarta.persistence.OneToOne(mappedBy = "paciente", cascade = jakarta.persistence.CascadeType.ALL, orphanRemoval = true)
    private HistoriaClinica historiaClinica;

    public Paciente() {
    }

    public Paciente(Long id, TipoDocumento tipoDocumento, String documento, Integer edad, String primerNombre,
            String segundoNombre, String primerApellido, String segundoApellido, LocalDate fechaNacimiento,
            String genero, String correoElectronico, String direccionResidencia, String eps,
            boolean requierePagoAdelantado) {
        this.id = id;
        this.tipoDocumento = tipoDocumento;
        this.documento = documento;
        this.edad = edad;
        this.primerNombre = primerNombre;
        this.segundoNombre = segundoNombre;
        this.primerApellido = primerApellido;
        this.segundoApellido = segundoApellido;
        this.fechaNacimiento = fechaNacimiento;
        this.genero = genero;
        this.correoElectronico = correoElectronico;
        this.direccionResidencia = direccionResidencia;
        this.eps = eps;
        this.requierePagoAdelantado = requierePagoAdelantado;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public TipoDocumento getTipoDocumento() {
        return tipoDocumento;
    }

    public void setTipoDocumento(TipoDocumento tipoDocumento) {
        this.tipoDocumento = tipoDocumento;
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

    public String getPrimerNombre() {
        return primerNombre;
    }

    public void setPrimerNombre(String primerNombre) {
        this.primerNombre = primerNombre;
    }

    public String getSegundoNombre() {
        return segundoNombre;
    }

    public void setSegundoNombre(String segundoNombre) {
        this.segundoNombre = segundoNombre;
    }

    public String getPrimerApellido() {
        return primerApellido;
    }

    public void setPrimerApellido(String primerApellido) {
        this.primerApellido = primerApellido;
    }

    public String getSegundoApellido() {
        return segundoApellido;
    }

    public void setSegundoApellido(String segundoApellido) {
        this.segundoApellido = segundoApellido;
    }

    public LocalDate getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(LocalDate fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getCorreoElectronico() {
        return correoElectronico;
    }

    public void setCorreoElectronico(String correoElectronico) {
        this.correoElectronico = correoElectronico;
    }

    public String getDireccionResidencia() {
        return direccionResidencia;
    }

    public void setDireccionResidencia(String direccionResidencia) {
        this.direccionResidencia = direccionResidencia;
    }

    public String getEps() {
        return eps;
    }

    public void setEps(String eps) {
        this.eps = eps;
    }

    public boolean isRequierePagoAdelantado() {
        return requierePagoAdelantado;
    }

    public void setRequierePagoAdelantado(boolean requierePagoAdelantado) {
        this.requierePagoAdelantado = requierePagoAdelantado;
    }
}
