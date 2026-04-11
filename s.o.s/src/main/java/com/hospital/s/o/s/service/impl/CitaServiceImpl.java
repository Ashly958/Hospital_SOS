package com.hospital.s.o.s.service.impl;

import com.hospital.s.o.s.model.dto.CitaDTO;
import com.hospital.s.o.s.model.entities.Cita;
import com.hospital.s.o.s.repository.CitaRepository;
import com.hospital.s.o.s.repository.ConsultorioRepository;
import com.hospital.s.o.s.repository.MedicoRepository;
import com.hospital.s.o.s.repository.PacienteRepository;
import com.hospital.s.o.s.service.CitaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.hospital.s.o.s.model.enums.EstadoCita;
import com.hospital.s.o.s.model.enums.EstadoAsistencia;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CitaServiceImpl implements CitaService {

    private final CitaRepository citaRepository;

    @Autowired
    private PacienteRepository pacienteRepository;

    @Autowired
    private MedicoRepository medicoRepository;

    @Autowired
    private ConsultorioRepository consultorioRepository;

    public CitaServiceImpl(CitaRepository citaRepository) {
        this.citaRepository = citaRepository;
    }

    @Override
    public CitaDTO guardar(CitaDTO dto) {
        Cita entidad = new Cita();

        if (dto.getId() != null) {
            entidad.setId(dto.getId());
        }

        // 1. PASAR LOS CAMPOS SIMPLES
        entidad.setFechaHora(dto.getFechaHora());
        entidad.setMotivoConsulta(dto.getMotivoConsulta());
        entidad.setCostoCita(dto.getCostoCita());
        entidad.setPagada(dto.getPagada());
        entidad.setTelemedicina(dto.getTelemedicina());

        // CONVERSIÓN DE STRING A ENUM (Esto quita lo rojo)
        if (dto.getEstadoCita() != null) {
            entidad.setEstadoCita(EstadoCita.valueOf(dto.getEstadoCita()));
        }
        if (dto.getEstadoAsistencia() != null) {
            entidad.setEstadoAsistencia(EstadoAsistencia.valueOf(dto.getEstadoAsistencia()));
        }

        // 2. BUSCAR Y ASIGNAR OBJETOS
        entidad.setPaciente(pacienteRepository.findById(dto.getPacienteId())
                .orElseThrow(() -> new RuntimeException("Paciente no encontrado")));

        entidad.setMedico(medicoRepository.findById(dto.getMedicoId())
                .orElseThrow(() -> new RuntimeException("Médico no encontrado")));

        entidad.setConsultorio(consultorioRepository.findById(dto.getConsultorioId())
                .orElseThrow(() -> new RuntimeException("Consultorio no encontrado")));

        Cita guardada = citaRepository.save(entidad);
        dto.setId(guardada.getId());
        return dto;
    }

    @Override
    public Optional<CitaDTO> buscarPorId(Long id) {
        return citaRepository.findById(id).map(this::convertirADTO);
    }

    @Override
    public List<CitaDTO> listarTodas() {
        return citaRepository.findAll().stream()
                .map(this::convertirADTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<CitaDTO> listarPorRangoFecha(LocalDateTime start, LocalDateTime end) {
        return citaRepository.findByFechaHoraBetween(start, end).stream()
                .map(this::convertirADTO)
                .collect(Collectors.toList());
    }

    @Override
    public void eliminar(Long id) {
        citaRepository.deleteById(id);
    }

    private CitaDTO convertirADTO(Cita cita) {
        CitaDTO dto = new CitaDTO();
        dto.setId(cita.getId());
        dto.setFechaHora(cita.getFechaHora());
        dto.setMotivoConsulta(cita.getMotivoConsulta());
        dto.setCostoCita(cita.getCostoCita());
        dto.setPagada(cita.getPagada());
        dto.setTelemedicina(cita.getTelemedicina());

        // CONVERSIÓN DE ENUM A STRING
        if (cita.getEstadoCita() != null)
            dto.setEstadoCita(cita.getEstadoCita().name());
        if (cita.getEstadoAsistencia() != null)
            dto.setEstadoAsistencia(cita.getEstadoAsistencia().name());

        if (cita.getMedico() != null)
            dto.setMedicoId(cita.getMedico().getId());
        if (cita.getPaciente() != null)
            dto.setPacienteId(cita.getPaciente().getId());
        if (cita.getConsultorio() != null)
            dto.setConsultorioId(cita.getConsultorio().getId());

        return dto;
    }
}