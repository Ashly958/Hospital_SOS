package com.hospital.s.o.s.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import com.hospital.s.o.s.model.dto.CitaDTO;

public interface CitaService {

    // Unificamos a guardar (sirve para crear y actualizar)
    CitaDTO guardar(CitaDTO dto);

    // Cambiamos a Optional<CitaDTO> para que el Controller funcione con el .map()
    Optional<CitaDTO> buscarPorId(Long id);

    List<CitaDTO> listarTodas();

    List<CitaDTO> listarPorRangoFecha(LocalDateTime start, LocalDateTime end);

    void eliminar(Long id);
}