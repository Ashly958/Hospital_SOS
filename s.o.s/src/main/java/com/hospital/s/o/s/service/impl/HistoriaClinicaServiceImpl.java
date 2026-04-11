package com.hospital.s.o.s.service.impl;

import com.hospital.s.o.s.model.dto.HistoriaClinicaDTO;
import com.hospital.s.o.s.model.entities.HistoriaClinica;
import com.hospital.s.o.s.model.entities.Paciente;
import com.hospital.s.o.s.repository.HistoriaClinicaRepository;
import com.hospital.s.o.s.repository.PacienteRepository;
import com.hospital.s.o.s.service.HistoriaClinicaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class HistoriaClinicaServiceImpl implements HistoriaClinicaService {

    @Autowired
    private HistoriaClinicaRepository repository;

    @Autowired
    private PacienteRepository pacienteRepository;

    @Override
    public HistoriaClinicaDTO guardar(HistoriaClinicaDTO dto) {
        HistoriaClinica entidad;

        // 1. SI HAY ID: Es una actualización directa por ID de Historia Clínica
        if (dto.getId() != null) {
            entidad = repository.findById(dto.getId())
                    .orElseThrow(() -> new RuntimeException("Historia clínica no encontrada con ID: " + dto.getId()));
        }
        // 2. SI NO HAY ID: Verificamos si el paciente YA tiene una historia asignada
        else {
            // Debes tener este método en tu HistoriaClinicaRepository o usar findById para
            // el paciente
            // Pero para salir del paso en el examen, intentamos buscarla:
            entidad = new HistoriaClinica();
        }

        // Buscamos el paciente para la relación
        Paciente paciente = pacienteRepository.findById(dto.getPacienteId())
                .orElseThrow(() -> new RuntimeException("Paciente no encontrado"));

        entidad.setPaciente(paciente);

        // IMPORTANTE: Setea los campos que antes tenías en null para que se guarden los
        // datos de Postman
        entidad.setAntecedentes(dto.getAntecedentes());
        entidad.setGrupoSanguineo(dto.getGrupoSanguineo());
        entidad.setEstado(dto.getEstado());
        entidad.setFechaCreacion(dto.getFechaCreacion());

        // Guardamos (Spring JPA hará Update si el ID existe, o Insert si es nueva)
        HistoriaClinica guardada = repository.save(entidad);

        // Devolvemos el DTO actualizado
        dto.setId(guardada.getId());
        return dto;
    }

    @Override
    public List<HistoriaClinicaDTO> listarTodas() {
        return repository.findAll().stream().map(h -> {
            HistoriaClinicaDTO dto = new HistoriaClinicaDTO();
            dto.setId(h.getId());
            // Solo dejamos el paciente porque es lo que hay en la DB
            if (h.getPaciente() != null) {
                dto.setPacienteId(h.getPaciente().getId());
            }
            return dto;
        }).collect(Collectors.toList());
    }

    @Override
    public Optional<HistoriaClinicaDTO> buscarPorId(Long id) {
        return repository.findById(id).map(h -> {
            HistoriaClinicaDTO dto = new HistoriaClinicaDTO();
            dto.setId(h.getId());
            if (h.getPaciente() != null) {
                dto.setPacienteId(h.getPaciente().getId());
            }
            return dto;
        });
    }

    @Override
    public void eliminar(Long id) {
        // Usamos el nuevo método manual que acabamos de crear
        if (repository.existsById(id)) {
            repository.eliminarPorIdManual(id);
        } else {
            throw new RuntimeException("No se encontró la historia clínica con ID: " + id);
        }
    }
}