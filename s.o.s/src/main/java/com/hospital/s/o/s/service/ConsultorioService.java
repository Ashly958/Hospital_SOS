package com.hospital.s.o.s.service;

import com.hospital.s.o.s.model.dto.ConsultorioDTO;
import java.util.List;

public interface ConsultorioService {
    ConsultorioDTO guardar(ConsultorioDTO dto);
    List<ConsultorioDTO> listarTodos();
    ConsultorioDTO buscarPorId(Long id);
    void eliminar(Long id);
}
