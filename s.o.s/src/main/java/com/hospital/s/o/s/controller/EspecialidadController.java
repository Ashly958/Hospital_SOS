package com.hospital.s.o.s.controller;

import com.hospital.s.o.s.model.entities.Especialidad;
import com.hospital.s.o.s.repository.EspecialidadRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/especialidades")
@CrossOrigin(origins = "*") // Por si luego conectas el Front-end
public class EspecialidadController {

    @Autowired
    private EspecialidadRepository especialidadRepository;

    // LISTAR TODAS
    @GetMapping
    public List<Especialidad> listar() {
        return especialidadRepository.findAll();
    }

    // BUSCAR POR ID
    @GetMapping("/{id}")
    public ResponseEntity<Especialidad> buscarPorId(@PathVariable Long id) {
        return especialidadRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // CREAR (El que necesitabas)
    @PostMapping
    public Especialidad guardar(@RequestBody Especialidad especialidad) {
        return especialidadRepository.save(especialidad);
    }

    // ACTUALIZAR
    @PutMapping("/{id}")
    public ResponseEntity<Especialidad> actualizar(@PathVariable Long id, @RequestBody Especialidad detalles) {
        return especialidadRepository.findById(id)
                .map(especialidad -> {
                    especialidad.setNombre(detalles.getNombre());
                    especialidad.setDescripcion(detalles.getDescripcion());
                    return ResponseEntity.ok(especialidadRepository.save(especialidad));
                }).orElse(ResponseEntity.notFound().build());
    }

    // ELIMINAR
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        if (especialidadRepository.existsById(id)) {
            especialidadRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}