package com.hospital.s.o.s.service;

import java.util.List;
import java.util.Optional;

import com.hospital.s.o.s.model.entities.Especialidad;
import com.hospital.s.o.s.model.entities.Medico;

public interface MedicoService {

    Medico save(Medico medico);

    Optional<Medico> buscarPorId(Long id);

    List<Medico> buscarPorEspecialidad(Especialidad especialidad);

    List<Medico> listarTodosLosMedicos();

    void eliminar(Long id);
}
