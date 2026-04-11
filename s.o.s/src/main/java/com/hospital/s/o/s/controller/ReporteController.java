package com.hospital.s.o.s.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import java.util.Map;
import java.util.HashMap;

import com.hospital.s.o.s.model.dto.EstadisticasDTO;
import com.hospital.s.o.s.repository.PacienteRepository;
import com.hospital.s.o.s.service.CitaService;
import com.hospital.s.o.s.service.TurnoService;
import com.hospital.s.o.s.repository.MedicoRepository;
import com.hospital.s.o.s.repository.MantenimientoRepository;
import com.hospital.s.o.s.repository.CancelacionCitaRepository;

@RestController
@RequestMapping("/api/v1/reportes")
public class ReporteController {

    @Autowired
    private PacienteRepository pacienteRepository;

    @Autowired
    private MedicoRepository medicoRepository;

    @Autowired
    private MantenimientoRepository mantenimientoRepository;

    @Autowired
    private CancelacionCitaRepository cancelacionRepository;

    @GetMapping("/estadisticas")
    public EstadisticasDTO obtenerEstadisticasVariables() {
        EstadisticasDTO dto = new EstadisticasDTO();
        dto.setTotalPacientes(pacienteRepository.count());
        dto.setTotalMedicos(medicoRepository.count());
        dto.setTotalMantenimientos(mantenimientoRepository.count());
        dto.setTotalCancelaciones(cancelacionRepository.count());

        return dto;
    }

    @GetMapping("/promedio-edades")
    public Map<String, Object> obtenerPromedios() {
        // En un caso real usaría AVG en @Query, aquí agrupamos por software.
        long count = pacienteRepository.count();
        int sumEdades = pacienteRepository.findAll().stream()
                .mapToInt(p -> p.getEdad() != null ? p.getEdad() : 0)
                .sum();

        double promedio = count > 0 ? (double) sumEdades / count : 0.0;

        Map<String, Object> response = new HashMap<>();
        response.put("total_registros", count);
        response.put("promedio_edad_pacientes", promedio);
        return response;
    }

    @Autowired
    private CitaService citaService;

    @Autowired
    private TurnoService turnoService;

    @GetMapping("/general")
    public ResponseEntity<Map<String, Object>> generarReporte() {
        Map<String, Object> reporte = new HashMap<>();

        int totalCitas = citaService.listarTodas().size();
        int totalTurnos = turnoService.listarTodosLosTurnos().size();

        reporte.put("titulo", "Reporte General Hospital S.O.S");
        reporte.put("cantidad_citas", totalCitas);
        reporte.put("cantidad_turnos", totalTurnos);
        reporte.put("estado_servicios", "Activo");
        reporte.put("mensaje", "Resumen de procesos ejecutados");

        return ResponseEntity.ok(reporte);
    }

    @Autowired
    private com.hospital.s.o.s.repository.CitaRepository citaRepository; // Asegúrate de tener esto

    @GetMapping("/medico/{id}/total-citas")
    public ResponseEntity<Map<String, Object>> citasPorMedico(@PathVariable Long id) {
        // Usamos el repositorio directamente para filtrar
        long total = citaRepository.findAll().stream()
                .filter(c -> c.getMedico() != null && c.getMedico().getId().equals(id))
                .count();

        Map<String, Object> response = new HashMap<>();
        response.put("medico_id", id);
        response.put("total_citas_asignadas", total);
        return ResponseEntity.ok(response);
    }
}
