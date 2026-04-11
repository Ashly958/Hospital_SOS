package com.hospital.s.o.s.controller;

import com.hospital.s.o.s.model.dto.TurnoDTO;
import com.hospital.s.o.s.service.TurnoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/turnos")
public class TurnoController {

    @Autowired
    private TurnoService turnoService;

    @PostMapping
    public ResponseEntity<TurnoDTO> crearTurno(@Valid @RequestBody TurnoDTO dto) {
        return new ResponseEntity<>(turnoService.guardar(dto), HttpStatus.CREATED);
    }

    @GetMapping("/medico/{medicoId}")
    public ResponseEntity<List<TurnoDTO>> listarPorMedico(@PathVariable Long medicoId) {
        return ResponseEntity.ok(turnoService.buscarPorMedico(medicoId));
    }

    @GetMapping("/{id}")
    public ResponseEntity<TurnoDTO> buscarPorId(@PathVariable Long id) {
        return turnoService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<TurnoDTO> actualizarTurno(@PathVariable Long id, @RequestBody TurnoDTO dto) { // Quitamos //
                                                                                                        // @Valid
        dto.setId(id);
        return ResponseEntity.ok(turnoService.guardar(dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarTurno(@PathVariable Long id) {
        turnoService.eliminar(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<?> listarTodos() {
        return ResponseEntity.ok(turnoService.listarTodosLosTurnos());
    }
}