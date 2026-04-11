package com.hospital.s.o.s.controller;

import com.hospital.s.o.s.model.dto.CancelacionDTO;
import com.hospital.s.o.s.service.CancelacionService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/cancelaciones")
public class CancelacionController {

    @Autowired
    private CancelacionService cancelacionService;

    @PostMapping
    public ResponseEntity<CancelacionDTO> crearCancelacion(@Valid @RequestBody CancelacionDTO dto) {
        return new ResponseEntity<>(cancelacionService.guardar(dto), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<CancelacionDTO>> listarCancelaciones() {
        return ResponseEntity.ok(cancelacionService.listarTodas());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CancelacionDTO> buscarPorId(@PathVariable Long id) {
        return cancelacionService.buscarPorId(id) // Aquí ya no habrá rojo
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<CancelacionDTO> actualizar(@PathVariable Long id, @Valid @RequestBody CancelacionDTO dto) {
        dto.setId(id);
        return ResponseEntity.ok(cancelacionService.guardar(dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        cancelacionService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
