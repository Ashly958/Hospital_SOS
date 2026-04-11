package com.hospital.s.o.s.service;

import com.hospital.s.o.s.model.dto.PacienteDTO;
import java.util.List;

public interface PacienteService {
    PacienteDTO guardar(PacienteDTO dto);
    PacienteDTO actualizar(Long id, PacienteDTO dto);
    List<PacienteDTO> listarPacientes();
    PacienteDTO buscarPorId(Long id);
    void eliminar(Long id);
}
