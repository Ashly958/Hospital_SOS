package com.hospital.s.o.s.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hospital.s.o.s.model.entities.Mantenimiento;

import java.time.LocalDateTime;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

@Repository
public interface MantenimientoRepository extends JpaRepository<Mantenimiento, Long> {
    // Verifica si un consultorio está "bajo llave" por el técnico en una hora
    // específica
    @Query("SELECT CASE WHEN COUNT(m) > 0 THEN true ELSE false END FROM Mantenimiento m WHERE m.consultorio.id = :id " +
            "AND :fechaCita BETWEEN m.fechaInicio AND m.fechaFin")
    boolean isConsultorioEnMantenimiento(@Param("id") Long id, @Param("fechaCita") LocalDateTime fechaCita);
}
