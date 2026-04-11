package com.hospital.s.o.s.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.hospital.s.o.s.model.dto.PacienteDTO;
import com.hospital.s.o.s.service.PacienteService;
import java.util.List;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/pacientes")
public class PacienteController {

    @Autowired
    private PacienteService pacienteService;

    @PostMapping
    public ResponseEntity<PacienteDTO> registrar(@Valid @RequestBody PacienteDTO dto) {
        return new ResponseEntity<>(pacienteService.guardar(dto), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<PacienteDTO>> listarTodos() {
        return ResponseEntity.ok(pacienteService.listarPacientes());
    }

    @GetMapping("/{id}")
    public ResponseEntity<PacienteDTO> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(pacienteService.buscarPorId(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<PacienteDTO> actualizar(@PathVariable Long id, @Valid @RequestBody PacienteDTO dto) {
        return ResponseEntity.ok(pacienteService.actualizar(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        pacienteService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
