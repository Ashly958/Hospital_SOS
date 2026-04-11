package com.hospital.s.o.s.service;

import com.hospital.s.o.s.model.dto.HistoriaClinicaDTO;
import java.util.List;
import java.util.Optional;

public interface HistoriaClinicaService {
    HistoriaClinicaDTO guardar(HistoriaClinicaDTO dto);

    List<HistoriaClinicaDTO> listarTodas();

    Optional<HistoriaClinicaDTO> buscarPorId(Long id);

    void eliminar(Long id);
}