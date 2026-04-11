package com.hospital.s.o.s.service;

import com.hospital.s.o.s.model.dto.DiagnosticoDTO;
import java.util.List;

public interface DiagnosticoService {

    /**
     * Guarda un nuevo diagnóstico o actualiza uno existente.
     * 
     * @param dto Datos del diagnóstico.
     * @return El diagnóstico guardado con su ID asignado.
     */
    DiagnosticoDTO guardar(DiagnosticoDTO dto);

    /**
     * Lista todos los diagnósticos registrados.
     * 
     * @return Lista de DiagnosticoDTO.
     */
    List<DiagnosticoDTO> listarTodos();

    /**
     * Lista los diagnósticos asociados a un paciente específico.
     * 
     * @param pacienteId ID del paciente.
     * @return Lista de DiagnosticoDTO.
     */
    List<DiagnosticoDTO> listarPorPaciente(Long pacienteId);

    /**
     * Elimina un diagnóstico por su ID.
     * 
     * @param id ID del diagnóstico a eliminar.
     */
    void eliminar(Long id);
}