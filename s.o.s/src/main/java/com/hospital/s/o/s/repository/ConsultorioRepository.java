package com.hospital.s.o.s.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hospital.s.o.s.model.entities.Consultorio;

import java.util.List;
import org.springframework.data.jpa.repository.Query;

@Repository
public interface ConsultorioRepository extends JpaRepository<Consultorio, Long> {
    List<Consultorio> findByPiso(Integer piso);

    @Query("SELECT c FROM Consultorio c WHERE c.tieneEquiposEspeciales = true")
    List<Consultorio> findEquippedConsultorios();
}
