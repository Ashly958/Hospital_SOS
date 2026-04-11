package com.hospital.s.o.s.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hospital.s.o.s.model.entities.Paciente;

import java.util.List;
import java.util.Optional;

@Repository
public interface PacienteRepository extends JpaRepository<Paciente, Long> {
    // Para validar que no existan 2 clientes con la misma cédula (Req. 4.0)
    boolean existsByDocumento(String documento);

    // Buscar para reportes de morosidad
    List<Paciente> findByRequierePagoAdelantadoTrue();

    Optional<Paciente> findByDocumento(String documento);

    // BUSCAR PARA ACTUALIZAR O CONSULTAR
    @Override
    Optional<Paciente> findById(Long id);

    // ELIMINAR
    @Override
    void deleteById(Long id);

    // VALIDACIÓN (Para no registrar dos veces el mismo documento)
    boolean existsById(Long id);
}
