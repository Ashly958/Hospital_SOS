package com.hospital.s.o.s.repository;

import com.hospital.s.o.s.model.entities.Turno;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface TurnoRepository extends JpaRepository<Turno, Long> {

    // Esta es la línea que te falta para que el Service deje de salir en rojo:
    List<Turno> findByMedicoIdAndFecha(Long medicoId, LocalDate fecha);
    
    // Buscar turnos de un médico (Te faltaba esta otra)
    List<Turno> findByMedicoId(Long medicoId);
}
