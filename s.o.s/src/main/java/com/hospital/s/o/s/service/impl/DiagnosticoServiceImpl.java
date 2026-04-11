package com.hospital.s.o.s.service.impl;

import com.hospital.s.o.s.model.dto.DiagnosticoDTO;
import com.hospital.s.o.s.model.entities.*;
import com.hospital.s.o.s.repository.*;
import com.hospital.s.o.s.service.DiagnosticoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.stream.Collectors;
import java.time.LocalDateTime;

@Service
public class DiagnosticoServiceImpl implements DiagnosticoService {

    @Autowired
    private DiagnosticoRepository diagnosticoRepository;
    @Autowired
    private CitaRepository citaRepository;
    @Autowired
    private HistoriaClinicaRepository historiaRepository;

    @Override
    @Transactional
    public DiagnosticoDTO guardar(DiagnosticoDTO dto) {
        Diagnostico entidad;

        // Validar si es actualización o creación
        if (dto.getId() != null && dto.getId() > 0) {
            entidad = diagnosticoRepository.findById(dto.getId())
                    .orElse(new Diagnostico());
        } else {
            entidad = new Diagnostico();
        }

        // Validaciones de negocio
        if (dto.getDescripcion() == null || dto.getDescripcion().isEmpty()) {
            throw new RuntimeException("La descripción no puede estar vacía");
        }

        entidad.setDescripcion(dto.getDescripcion());
        entidad.setFecha(dto.getFecha() != null ? dto.getFecha() : LocalDateTime.now());

        // Cargar relaciones de forma segura
        HistoriaClinica historia = historiaRepository.findById(dto.getHistoriaId())
                .orElseThrow(() -> new RuntimeException("Historia no encontrada ID: " + dto.getHistoriaId()));
        entidad.setHistoriaClinica(historia);

        Cita cita = citaRepository.findById(dto.getCitaId())
                .orElseThrow(() -> new RuntimeException("Cita no encontrada ID: " + dto.getCitaId()));
        entidad.setCita(cita);

        // Guardar y limpiar caché de JPA
        Diagnostico guardado = diagnosticoRepository.saveAndFlush(entidad);

        dto.setId(guardado.getId());
        return dto;
    }

    @Override
    @Transactional(readOnly = true)
    public List<DiagnosticoDTO> listarTodos() {
        return diagnosticoRepository.findAll().stream()
                .map(this::convertirADTO)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public void eliminar(Long id) {
        // Verificamos si existe antes de intentar el borrado rudo
        if (!diagnosticoRepository.existsById(id)) {
            throw new RuntimeException("No se encontró el diagnóstico con ID: " + id);
        }

        // Llamamos al borrado nativo que no perdona
        diagnosticoRepository.hardDelete(id);

        // Forzamos a que la base de datos se entere YA
        diagnosticoRepository.flush();
    }

    private DiagnosticoDTO convertirADTO(Diagnostico entidad) {
        DiagnosticoDTO dto = new DiagnosticoDTO();
        dto.setId(entidad.getId());
        dto.setDescripcion(entidad.getDescripcion());
        dto.setFecha(entidad.getFecha());
        dto.setHistoriaId(entidad.getHistoriaClinica().getId());
        dto.setCitaId(entidad.getCita().getId());
        return dto;
    }

    @Override
    @Transactional(readOnly = true)
    public List<DiagnosticoDTO> listarPorPaciente(Long pacienteId) {
        return diagnosticoRepository.findByHistoriaClinicaPacienteId(pacienteId)
                .stream()
                .map(this::convertirADTO)
                .collect(Collectors.toList());
    }
}