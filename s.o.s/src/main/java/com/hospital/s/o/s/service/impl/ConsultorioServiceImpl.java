package com.hospital.s.o.s.service.impl;

import com.hospital.s.o.s.model.dto.ConsultorioDTO;
import com.hospital.s.o.s.model.entities.Consultorio;
import com.hospital.s.o.s.repository.ConsultorioRepository;
import com.hospital.s.o.s.service.ConsultorioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ConsultorioServiceImpl implements ConsultorioService {

    @Autowired
    private ConsultorioRepository consultorioRepository;

    @Override
    public ConsultorioDTO guardar(ConsultorioDTO dto) {
        Consultorio consultorio = new Consultorio();
        if (dto.getId() != null) {
            consultorio.setId(dto.getId());
        }
        consultorio.setNombre(dto.getNombre());
        consultorio.setPiso(dto.getPiso());
        consultorio.setTieneEquiposEspeciales(dto.getTieneEquiposEspeciales() != null ? dto.getTieneEquiposEspeciales() : false);
        consultorio.setCapacidadMaxima(dto.getCapacidadMaxima());

        Consultorio guardado = consultorioRepository.save(consultorio);
        return convertirADTO(guardado);
    }

    @Override
    public List<ConsultorioDTO> listarTodos() {
        return consultorioRepository.findAll().stream()
                .map(this::convertirADTO)
                .collect(Collectors.toList());
    }

    @Override
    public ConsultorioDTO buscarPorId(Long id) {
        Consultorio consultorio = consultorioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Consultorio no encontrado con ID: " + id));
        return convertirADTO(consultorio);
    }

    @Override
    public void eliminar(Long id) {
        consultorioRepository.deleteById(id);
    }

    private ConsultorioDTO convertirADTO(Consultorio consultorio) {
        ConsultorioDTO dto = new ConsultorioDTO();
        dto.setId(consultorio.getId());
        dto.setNombre(consultorio.getNombre());
        dto.setPiso(consultorio.getPiso());
        dto.setTieneEquiposEspeciales(consultorio.getTieneEquiposEspeciales());
        dto.setCapacidadMaxima(consultorio.getCapacidadMaxima());
        return dto;
    }
}
