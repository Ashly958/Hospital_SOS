package com.hospital.s.o.s.service.impl;

import com.hospital.s.o.s.service.TurnoService;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import com.hospital.s.o.s.model.dto.TurnoDTO;
import com.hospital.s.o.s.model.entities.Turno;
import com.hospital.s.o.s.model.entities.Medico;
import com.hospital.s.o.s.repository.TurnoRepository;
import com.hospital.s.o.s.repository.MedicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class TurnoServiceImpl implements TurnoService {

    @Autowired
    private TurnoRepository repository;

    @Autowired
    private MedicoRepository medicoRepository;

    @Override
    public Turno save(Turno turno) {
        return this.repository.save(turno);
    }

    @Override
    public TurnoDTO guardar(TurnoDTO dto) {
        Medico medico = medicoRepository.findById(dto.getMedicoId())
                .orElseThrow(() -> new RuntimeException("Médico no encontrado con ID: " + dto.getMedicoId()));

        java.time.LocalTime inicio = dto.getHoraInicio();
        java.time.LocalTime fin = dto.getHoraFin();

        // ... (Mantén tus validaciones de horas y cruces igual aquí) ...

        Turno turno;
        // CLAVE: Si el DTO trae ID, buscamos el turno real de la DB
        if (dto.getId() != null) {
            turno = repository.findById(dto.getId())
                    .orElseThrow(() -> new RuntimeException("Turno no encontrado con ID: " + dto.getId()));
        } else {
            // Si no trae ID, es un registro nuevo (POST)
            turno = new Turno();
        }

        // Seteamos los valores a la entidad (ya sea la nueva o la encontrada)
        turno.setFecha(dto.getFecha());
        turno.setHoraInicio(inicio);
        turno.setHoraFin(fin);
        turno.setMedico(medico);

        Turno guardado = this.repository.save(turno);
        return convertirADTO(guardado);
    }

    @Override
    public List<TurnoDTO> buscarPorMedico(Long medicoId) {
        return this.repository.findByMedicoId(medicoId)
                .stream()
                .map(this::convertirADTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<Turno> listarTodosLosTurnos() {
        return this.repository.findAll();
    }

    @Override
    public List<Turno> buscarMedicoPorTurno(Long medicoId, LocalDate fecha) {
        return this.repository.findByMedicoIdAndFecha(medicoId, fecha);
    }

    @Override
    public void eliminar(Long id) {
        this.repository.deleteById(id);
    }

    private TurnoDTO convertirADTO(Turno turno) {
        TurnoDTO dto = new TurnoDTO();
        dto.setId(turno.getId()); // <-- ¡Añade esta línea!
        dto.setFecha(turno.getFecha());
        dto.setHoraInicio(turno.getHoraInicio());
        dto.setHoraFin(turno.getHoraFin());
        if (turno.getMedico() != null) {
            dto.setMedicoId(turno.getMedico().getId());
        }
        return dto;
    }

    @Override
    public Optional<TurnoDTO> buscarPorId(Long id) {
        return repository.findById(id).map(this::convertirADTO);
    }
}