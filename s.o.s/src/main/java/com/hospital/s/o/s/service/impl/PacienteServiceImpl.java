package com.hospital.s.o.s.service.impl;

import com.hospital.s.o.s.model.dto.PacienteDTO;
import com.hospital.s.o.s.model.entities.Paciente;
import com.hospital.s.o.s.repository.PacienteRepository;
import com.hospital.s.o.s.service.PacienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PacienteServiceImpl implements PacienteService {

    @Autowired
    private PacienteRepository repository;

    @Override
    public PacienteDTO guardar(PacienteDTO dto) {
        if (repository.existsByDocumento(dto.getDocumento())) {
            throw new RuntimeException("Regla de Negocio: Ya existe un paciente con el número de cédula/documento: " + dto.getDocumento());
        }
        Paciente p = mapearAEntidad(dto, new Paciente());
        return mapearADto(repository.save(p));
    }

    @Override
    public PacienteDTO actualizar(Long id, PacienteDTO dto) {
        Paciente p = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Paciente no encontrado"));
        
        if (!p.getDocumento().equals(dto.getDocumento()) && repository.existsByDocumento(dto.getDocumento())) {
            throw new RuntimeException("Regla de Negocio: Ya existe otro paciente con ese documento");
        }
        
        Paciente actualizado = repository.save(mapearAEntidad(dto, p));
        return mapearADto(actualizado);
    }

    @Override
    public List<PacienteDTO> listarPacientes() {
        return repository.findAll().stream().map(this::mapearADto).collect(Collectors.toList());
    }

    @Override
    public PacienteDTO buscarPorId(Long id) {
        return mapearADto(repository.findById(id).orElseThrow(() -> new RuntimeException("No encontrado")));
    }

    @Override
    public void eliminar(Long id) {
        repository.deleteById(id);
    }

    private Paciente mapearAEntidad(PacienteDTO dto, Paciente p) {
        p.setTipoDocumento(dto.getTipoDocumento());
        p.setDocumento(dto.getDocumento());
        p.setPrimerNombre(dto.getPrimerNombre());
        p.setSegundoNombre(dto.getSegundoNombre());
        p.setPrimerApellido(dto.getPrimerApellido());
        p.setSegundoApellido(dto.getSegundoApellido());
        p.setCorreoElectronico(dto.getCorreoElectronico());
        p.setEdad(dto.getEdad());
        p.setFechaNacimiento(dto.getFechaNacimiento());
        p.setGenero(dto.getGenero());
        p.setDireccionResidencia(dto.getDireccionResidencia());
        p.setEps(dto.getEps());
        return p;
    }

    private PacienteDTO mapearADto(Paciente p) {
        PacienteDTO dto = new PacienteDTO();
        dto.setId(p.getId());
        dto.setTipoDocumento(p.getTipoDocumento());
        dto.setDocumento(p.getDocumento());
        dto.setPrimerNombre(p.getPrimerNombre());
        dto.setSegundoNombre(p.getSegundoNombre());
        dto.setPrimerApellido(p.getPrimerApellido());
        dto.setSegundoApellido(p.getSegundoApellido());
        dto.setCorreoElectronico(p.getCorreoElectronico());
        dto.setEdad(p.getEdad());
        dto.setFechaNacimiento(p.getFechaNacimiento());
        dto.setGenero(p.getGenero());
        dto.setDireccionResidencia(p.getDireccionResidencia());
        dto.setEps(p.getEps());
        return dto;
    }
}
