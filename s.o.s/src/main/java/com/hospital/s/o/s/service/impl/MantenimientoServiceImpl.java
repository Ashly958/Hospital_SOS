package com.hospital.s.o.s.service.impl;

import com.hospital.s.o.s.model.dto.MantenimientoDTO;
import com.hospital.s.o.s.model.entities.Mantenimiento;
import com.hospital.s.o.s.model.entities.Consultorio;
import com.hospital.s.o.s.repository.MantenimientoRepository;
import com.hospital.s.o.s.repository.ConsultorioRepository;
import com.hospital.s.o.s.service.MantenimientoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MantenimientoServiceImpl implements MantenimientoService {

    @Autowired
    private MantenimientoRepository mantenimientoRepository;

    @Autowired
    private ConsultorioRepository consultorioRepository;

    @Override
    public MantenimientoDTO guardar(MantenimientoDTO dto) {
        Consultorio consultorio = consultorioRepository.findById(dto.getConsultorioId())
                .orElseThrow(() -> new RuntimeException("Consultorio no encontrado con ID: " + dto.getConsultorioId()));

        Mantenimiento mantenimiento = new Mantenimiento();
        mantenimiento.setNombreTecnico(dto.getNombreTecnico());
        mantenimiento.setApellidoTecnico(dto.getApellidoTecnico());
        mantenimiento.setTipoDocumentoTecnico(dto.getTipoDocumentoTecnico());
        mantenimiento.setDocumentoTecnico(dto.getDocumentoTecnico());
        mantenimiento.setDescripcion(dto.getDescripcion());
        mantenimiento.setFechaInicio(dto.getFechaInicio());
        mantenimiento.setFechaFin(dto.getFechaFin());
        mantenimiento.setConsultorio(consultorio);

        Mantenimiento guardado = mantenimientoRepository.save(mantenimiento);
        return convertirADTO(guardado);
    }

    @Override
    public List<MantenimientoDTO> listarTodos() {
        return mantenimientoRepository.findAll().stream()
                .map(this::convertirADTO)
                .collect(Collectors.toList());
    }

    private MantenimientoDTO convertirADTO(Mantenimiento mantenimiento) {
        MantenimientoDTO dto = new MantenimientoDTO();
        dto.setId(mantenimiento.getId());
        dto.setNombreTecnico(mantenimiento.getNombreTecnico());
        dto.setApellidoTecnico(mantenimiento.getApellidoTecnico());
        dto.setTipoDocumentoTecnico(mantenimiento.getTipoDocumentoTecnico());
        dto.setDocumentoTecnico(mantenimiento.getDocumentoTecnico());
        dto.setDescripcion(mantenimiento.getDescripcion());
        dto.setFechaInicio(mantenimiento.getFechaInicio());
        dto.setFechaFin(mantenimiento.getFechaFin());
        if (mantenimiento.getConsultorio() != null) {
            dto.setConsultorioId(mantenimiento.getConsultorio().getId());
        }
        return dto;
    }

    @Override
    public Optional<MantenimientoDTO> buscarPorId(Long id) {
        return mantenimientoRepository.findById(id).map(this::convertirADTO);
    }

    @Override
    public void eliminar(Long id) {
        mantenimientoRepository.deleteById(id);
    }
}
