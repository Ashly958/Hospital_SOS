package com.hospital.s.o.s.service;

import com.hospital.s.o.s.model.dto.MantenimientoDTO;
import java.util.List;
import java.util.Optional;

public interface MantenimientoService {
    MantenimientoDTO guardar(MantenimientoDTO dto);

    List<MantenimientoDTO> listarTodos();

    Optional<MantenimientoDTO> buscarPorId(Long id);

    void eliminar(Long id);
}
