package com.hospital.s.o.s.controller;

import com.hospital.s.o.s.model.dto.HistoriaClinicaDTO;
import com.hospital.s.o.s.service.HistoriaClinicaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/historias-clinicas")
@CrossOrigin(origins = "*") // Para que no tengas problemas de permisos
public class HistoriaClinicaController {

    @Autowired
    private HistoriaClinicaService historiaService;

    @PostMapping
    public ResponseEntity<HistoriaClinicaDTO> guardar(@RequestBody HistoriaClinicaDTO dto) {
        return new ResponseEntity<>(historiaService.guardar(dto), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<HistoriaClinicaDTO>> listarTodas() {
        return ResponseEntity.ok(historiaService.listarTodas());
    }

    @GetMapping("/{id}")
    public ResponseEntity<HistoriaClinicaDTO> buscarPorId(@PathVariable Long id) {
        return historiaService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        historiaService.eliminar(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<HistoriaClinicaDTO> actualizar(@PathVariable Long id, @RequestBody HistoriaClinicaDTO dto) {
        dto.setId(id); // Aseguramos que el ID de la URL se asigne al DTO
        return ResponseEntity.ok(historiaService.guardar(dto));
    }
}