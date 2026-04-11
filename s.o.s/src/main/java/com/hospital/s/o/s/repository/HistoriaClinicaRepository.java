package com.hospital.s.o.s.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying; // Importante
import org.springframework.data.jpa.repository.Query; // Importante
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional; // Importante

import com.hospital.s.o.s.model.entities.HistoriaClinica;

import java.util.Optional;

@Repository
public interface HistoriaClinicaRepository extends JpaRepository<HistoriaClinica, Long> {

    Optional<HistoriaClinica> findByPacienteId(Long pacienteId);

    Optional<HistoriaClinica> findByPacienteDocumento(String documento);

    // NUEVO MÉTODO PARA FORZAR EL BORRADO
    @Modifying
    @Transactional
    @Query("DELETE FROM HistoriaClinica h WHERE h.id = ?1")
    void eliminarPorIdManual(Long id);
}