package com.hospital.s.o.s.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.hospital.s.o.s.model.entities.Especialidad;
import com.hospital.s.o.s.model.entities.Medico;

import java.util.List;

@Repository
public interface MedicoRepository extends JpaRepository<Medico, Long> {
    // Requisito: Buscar médico por nombre de especialidad
    List<Medico> findByEspecialidadNombreContainingIgnoreCase(String especialidad);

    boolean existsByTarjetaProfesional(String tarjeta);

    @Query("SELECT m FROM Medico m WHERE m.activo = true")
    List<Medico> findAllActiveMedicos();

    List<Medico> findByEspecialidad(Especialidad especialidad);
}
