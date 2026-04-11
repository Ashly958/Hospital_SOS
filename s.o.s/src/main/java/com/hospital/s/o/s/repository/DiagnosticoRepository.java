package com.hospital.s.o.s.repository;

import com.hospital.s.o.s.model.entities.Diagnostico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface DiagnosticoRepository extends JpaRepository<Diagnostico, Long> {

    @Transactional
    @Modifying
    @Query(value = "DELETE FROM diagnosticos WHERE id = :id", nativeQuery = true)
    void hardDelete(@Param("id") Long id);

    List<Diagnostico> findByHistoriaClinicaPacienteId(Long pacienteId);
}