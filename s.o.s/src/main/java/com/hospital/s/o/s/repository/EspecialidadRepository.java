package com.hospital.s.o.s.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hospital.s.o.s.model.entities.Especialidad;

import java.util.Optional;

@Repository
public interface EspecialidadRepository extends JpaRepository<Especialidad, Long> {
    Optional<Especialidad> findByNombreIgnoreCase(String nombre);
}