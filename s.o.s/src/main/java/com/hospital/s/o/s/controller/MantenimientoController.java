package com.hospital.s.o.s.controller;

import com.hospital.s.o.s.model.dto.MantenimientoDTO;
import com.hospital.s.o.s.service.MantenimientoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/mantenimientos")
public class MantenimientoController {

    @Autowired
    private MantenimientoService mantenimientoService;

    @PostMapping
    public ResponseEntity<MantenimientoDTO> registrarMantenimiento(@Valid @RequestBody MantenimientoDTO dto) {
        return new ResponseEntity<>(mantenimientoService.guardar(dto), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<MantenimientoDTO>> listarMantenimientos() {
        return ResponseEntity.ok(mantenimientoService.listarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<MantenimientoDTO> buscarPorId(@PathVariable Long id) {
        return mantenimientoService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<MantenimientoDTO> actualizar(@PathVariable Long id,
            @Valid @RequestBody MantenimientoDTO dto) {
        dto.setId(id);
        return ResponseEntity.ok(mantenimientoService.guardar(dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        mantenimientoService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
