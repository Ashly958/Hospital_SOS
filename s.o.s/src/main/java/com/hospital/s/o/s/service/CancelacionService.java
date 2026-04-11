package com.hospital.s.o.s.service;

import com.hospital.s.o.s.model.dto.CancelacionDTO;
import java.util.Optional;

import java.util.List;

public interface CancelacionService {
    CancelacionDTO guardar(CancelacionDTO dto);

    List<CancelacionDTO> listarTodas();

    void eliminar(Long id);

    Optional<CancelacionDTO> buscarPorId(Long id);
}
