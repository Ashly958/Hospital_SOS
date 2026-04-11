package com.hospital.s.o.s.controller;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.hospital.s.o.s.service.DiagnosticoService;
import java.util.List;
import java.util.Map;

import com.hospital.s.o.s.model.dto.DiagnosticoDTO;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/diagnosticos")
public class DiagnosticoController {

    @Autowired
    private DiagnosticoService diagnosticoService;

    @PostMapping
    public ResponseEntity<?> crear(@Valid @RequestBody DiagnosticoDTO dto) {
        return new ResponseEntity<>(diagnosticoService.guardar(dto), HttpStatus.CREATED);
    }

    @GetMapping("/historia/{pacienteId}")
    public ResponseEntity<?> obtenerPorPaciente(@PathVariable Long pacienteId) {
        return ResponseEntity.ok(diagnosticoService.listarPorPaciente(pacienteId));
    }

    @GetMapping
    public ResponseEntity<List<DiagnosticoDTO>> listarTodos() {
        return ResponseEntity.ok(diagnosticoService.listarTodos());
    }

    @PutMapping("/{id}")
    public ResponseEntity<DiagnosticoDTO> actualizar(@PathVariable Long id, @RequestBody DiagnosticoDTO dto) {
        dto.setId(id); // <--- ESTA LÍNEA ES OBLIGATORIA
        return ResponseEntity.ok(diagnosticoService.guardar(dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        diagnosticoService.eliminar(id);
        return ResponseEntity.noContent().build();
    }

    // --- REPORTE JSON (Opción B) ---
    @GetMapping("/reportes/estadisticas")
    public ResponseEntity<?> obtenerReporteDiagnosticos() {
        Map<String, Object> reporte = new HashMap<>();
        reporte.put("total_registrados", diagnosticoService.listarTodos().size());
        reporte.put("sistema", "Hospital S.O.S - Módulo Clínico");
        return ResponseEntity.ok(reporte);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> obtenerPorId(@PathVariable Long id) {
        // Buscamos el diagnóstico directamente
        DiagnosticoDTO dto = diagnosticoService.listarTodos().stream()
                .filter(d -> d.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Diagnóstico no encontrado"));

        return ResponseEntity.ok(dto);
    }

}
