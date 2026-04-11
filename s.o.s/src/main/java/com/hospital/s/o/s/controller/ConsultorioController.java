package com.hospital.s.o.s.controller;

import com.hospital.s.o.s.model.dto.ConsultorioDTO;
import com.hospital.s.o.s.service.ConsultorioService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/consultorios")
public class ConsultorioController {

    @Autowired
    private ConsultorioService consultorioService;

    @PostMapping
    public ResponseEntity<ConsultorioDTO> guardarConsultorio(@Valid @RequestBody ConsultorioDTO dto) {
        return new ResponseEntity<>(consultorioService.guardar(dto), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<ConsultorioDTO>> listarConsultorios() {
        return ResponseEntity.ok(consultorioService.listarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ConsultorioDTO> buscarConsultorio(@PathVariable Long id) {
        return ResponseEntity.ok(consultorioService.buscarPorId(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ConsultorioDTO> actualizarConsultorio(@PathVariable Long id, @Valid @RequestBody ConsultorioDTO dto) {
        dto.setId(id);
        return ResponseEntity.ok(consultorioService.guardar(dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarConsultorio(@PathVariable Long id) {
        consultorioService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
