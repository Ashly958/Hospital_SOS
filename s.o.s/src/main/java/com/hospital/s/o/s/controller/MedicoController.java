package com.hospital.s.o.s.controller;

import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;

import com.hospital.s.o.s.model.entities.Medico;
import com.hospital.s.o.s.service.MedicoService;

@RestController
@RequestMapping("/api/v1/medicos")
public class MedicoController {

    @Autowired
    private MedicoService medicoService;

    @PostMapping
    public ResponseEntity<Medico> crear(@RequestBody Medico medico) {
        return ResponseEntity.ok(medicoService.save(medico));
    }

    @GetMapping
    public List<Medico> listarTodos() {
        return medicoService.listarTodosLosMedicos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Medico> buscarPorId(@PathVariable Long id) {
        return medicoService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Medico> actualizar(@PathVariable Long id, @RequestBody Medico medico) {
        // En un caso real se mapearía con un DTO. Aquí usamos la entidad directa por practicidad
        medico.setId(id);
        return ResponseEntity.ok(medicoService.save(medico));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        medicoService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
