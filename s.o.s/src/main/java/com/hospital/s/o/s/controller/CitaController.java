package com.hospital.s.o.s.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;

import com.hospital.s.o.s.service.CitaService;
import com.hospital.s.o.s.model.dto.CitaDTO;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.time.LocalDate;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;

@RestController
@RequestMapping("/api/v1/citas")
public class CitaController {

    @Autowired
    private CitaService citaService;

    @PostMapping("/agendar")
    public ResponseEntity<CitaDTO> agendar(@Valid @RequestBody CitaDTO dto) {
        return new ResponseEntity<>(citaService.guardar(dto), HttpStatus.CREATED);
    }

    // --- FALTA ESTE: Listar todas ---
    @GetMapping
    public ResponseEntity<List<CitaDTO>> listarTodas() {
        return ResponseEntity.ok(citaService.listarTodas());
    }

    // --- FALTA ESTE: Buscar por ID (Maneja el error 404 si no existe) ---
    @GetMapping("/{id}")
    public ResponseEntity<CitaDTO> buscarPorId(@PathVariable Long id) {
        return citaService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<CitaDTO> reprogramar(@PathVariable Long id, @Valid @RequestBody CitaDTO dto) {
        dto.setId(id);
        return ResponseEntity.ok(citaService.guardar(dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        citaService.eliminar(id);
        return ResponseEntity.noContent().build();
    }

    // --- REPORTES JSON (Para el 5.0 - Opción B) ---
    @GetMapping("/reportes/conteo")
    public ResponseEntity<?> obtenerResumenCitas() {
        List<CitaDTO> todas = citaService.listarTodas();
        Map<String, Object> reporte = new HashMap<>();
        reporte.put("total_citas", todas.size());
        reporte.put("mensaje", "Resumen de actividad generado exitosamente");
        reporte.put("fecha_reporte", LocalDate.now());
        return ResponseEntity.ok(reporte);
    }
}