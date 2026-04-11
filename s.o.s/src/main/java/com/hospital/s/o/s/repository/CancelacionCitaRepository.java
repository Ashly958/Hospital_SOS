package com.hospital.s.o.s.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;

import com.hospital.s.o.s.model.entities.CancelacionCita;

import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface CancelacionCitaRepository extends JpaRepository<CancelacionCita, Long> {
    // Para reportes de por qué la gente no asiste
    @Query("SELECT c.motivo, COUNT(c) FROM CancelacionCita c GROUP BY c.motivo")
    List<Object[]> countCancelacionesByMotivo();

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM cancelaciones WHERE id = :id", nativeQuery = true)
    void hardDelete(@Param("id") Long id);
}
