package com.hospital.s.o.s.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hospital.s.o.s.model.entities.Cita;
import com.hospital.s.o.s.model.enums.EstadoAsistencia;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.data.repository.query.Param;
import org.springframework.data.jpa.repository.Query;

@Repository
public interface CitaRepository extends JpaRepository<Cita, Long> {

    // VALIDACIÓN 1: No 2 citas con mismo médico al mismo tiempo
    boolean existsByMedicoIdAndFechaHora(Long medicoId, LocalDateTime fechaHora);

    // VALIDACIÓN 2: No 2 citas en el mismo consultorio al mismo tiempo
    boolean existsByConsultorioIdAndFechaHora(Long consultorioId, LocalDateTime fechaHora);

    // VALIDACIÓN 3: Máximo 4 citas por turno (8-12 o 2-6)
    @Query("SELECT COUNT(c) FROM Cita c WHERE c.medico.id = :medicoId AND c.fechaHora BETWEEN :inicio AND :fin")
    long countCitasInTurno(@Param("medicoId") Long medicoId, @Param("inicio") LocalDateTime inicio,
            @Param("fin") LocalDateTime fin);

    // VALIDACIÓN 4: Contar inasistencias para multar al paciente
    long countByPacienteIdAndEstadoAsistencia(Long pacienteId, EstadoAsistencia estado);

    // REPORTES 5.0: Citas por rango de fecha
    List<Cita> findByFechaHoraBetween(LocalDateTime start, LocalDateTime end);

}
