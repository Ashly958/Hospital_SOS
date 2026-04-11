package com.hospital.s.o.s.service.impl;

import com.hospital.s.o.s.model.dto.CancelacionDTO;
import com.hospital.s.o.s.model.entities.CancelacionCita;
import com.hospital.s.o.s.model.entities.Cita;
import com.hospital.s.o.s.model.enums.EstadoAsistencia;
import com.hospital.s.o.s.model.enums.EstadoCita;
import com.hospital.s.o.s.repository.CancelacionCitaRepository;
import com.hospital.s.o.s.repository.CitaRepository;
import com.hospital.s.o.s.service.CancelacionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CancelacionServiceImpl implements CancelacionService {

    @Autowired
    private CancelacionCitaRepository cancelacionRepository;

    @Autowired
    private CitaRepository citaRepository;

    @Override
    @Transactional
    public CancelacionDTO guardar(CancelacionDTO dto) {
        // 1. Buscamos la cita
        Cita cita = citaRepository.findById(dto.getCitaId())
                .orElseThrow(() -> new RuntimeException("Cita no encontrada con ID: " + dto.getCitaId()));

        // 2. CAMBIAMOS LOS ESTADOS DE LA CITA
        cita.setEstadoAsistencia(EstadoAsistencia.CANCELADA);
        cita.setEstadoCita(EstadoCita.CANCELADA);
        citaRepository.save(cita);

        // 3. LÓGICA DE ACTUALIZAR O CREAR
        CancelacionCita cancelacion;

        // Si el DTO trae ID, buscamos la cancelación existente para editarla
        if (dto.getId() != null && dto.getId() > 0) {
            cancelacion = cancelacionRepository.findById(dto.getId())
                    .orElse(new CancelacionCita()); // Si no existe, crea una nueva
        } else {
            cancelacion = new CancelacionCita(); // Es un POST
        }

        cancelacion.setFechaCancelacion(dto.getFechaCancelacion());
        cancelacion.setMotivo(dto.getMotivo());
        cancelacion.setCita(cita);

        CancelacionCita guardada = cancelacionRepository.save(cancelacion);
        return convertirADTO(guardada);
    }

    @Override
    public List<CancelacionDTO> listarTodas() {
        return cancelacionRepository.findAll().stream()
                .map(this::convertirADTO)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public void eliminar(Long id) {
        if (cancelacionRepository.existsById(id)) {
            // Usamos el hardDelete para obligar a MySQL a borrar la fila
            cancelacionRepository.hardDelete(id);

            // El flush es vital para que el cambio se vea en la BD antes de que termine la
            // petición
            cancelacionRepository.flush();
        } else {
            throw new RuntimeException("No se puede eliminar: Cancelación no encontrada");
        }
    }

    private CancelacionDTO convertirADTO(CancelacionCita cancelacion) {
        CancelacionDTO dto = new CancelacionDTO();
        dto.setId(cancelacion.getId());
        dto.setMotivo(cancelacion.getMotivo());
        dto.setFechaCancelacion(cancelacion.getFechaCancelacion()); // No olvides la fecha
        if (cancelacion.getCita() != null) {
            dto.setCitaId(cancelacion.getCita().getId());
        }
        return dto;
    }

    @Override
    public Optional<CancelacionDTO> buscarPorId(Long id) {
        return cancelacionRepository.findById(id)
                .map(this::convertirADTO);
    }
}