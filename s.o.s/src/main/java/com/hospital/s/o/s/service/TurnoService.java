package com.hospital.s.o.s.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import com.hospital.s.o.s.model.dto.TurnoDTO;
import com.hospital.s.o.s.model.entities.Turno;

public interface TurnoService {

    Turno save(Turno turno);

    // Cambiamos para que devuelva TurnoDTO y coincida con el Controller
    TurnoDTO guardar(TurnoDTO dto);

    List<Turno> buscarMedicoPorTurno(Long medicoId, LocalDate fecha);

    // Cambiamos para que devuelva List<TurnoDTO> y coincida con el Controller
    List<TurnoDTO> buscarPorMedico(Long medicoId);

    List<Turno> listarTodosLosTurnos();

    void eliminar(Long id);

    Optional<TurnoDTO> buscarPorId(Long id);
}
