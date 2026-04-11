package com.hospital.s.o.s.service.impl;

import com.hospital.s.o.s.service.MedicoService;
import java.util.List;
import java.util.Optional;

import com.hospital.s.o.s.model.entities.Especialidad;
import com.hospital.s.o.s.model.entities.Medico;
import com.hospital.s.o.s.repository.MedicoRepository;
import org.springframework.stereotype.Service;

@Service
public class MedicoServiceImpl implements MedicoService {

    private final MedicoRepository repository;

    public MedicoServiceImpl(MedicoRepository repository) {
        this.repository = repository;
    }

    @Override
    public Medico save(Medico medico) {
        return this.repository.save(medico);
    }

    @Override
    public List<Medico> listarTodosLosMedicos() {
        return this.repository.findAll();
    }

    @Override
    public Optional<Medico> buscarPorId(Long id) {
        return this.repository.findById(id);
    }

    @Override
    public List<Medico> buscarPorEspecialidad(Especialidad especialidad) {
        return this.repository.findByEspecialidad(especialidad);
    }

    @Override
    public void eliminar(Long id) {
        this.repository.deleteById(id);
    }
}
