package com.cloudNativeBack.cloudNativeBack.controller;

import org.springframework.beans.factory.annotation.Autowired;

import com.cloudNativeBack.cloudNativeBack.model.Paciente;
import com.cloudNativeBack.cloudNativeBack.model.dto.PacienteUpdateDTO;
import com.cloudNativeBack.cloudNativeBack.service.PacienteService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController

@RequestMapping("/back/pacientes")
public class PacienteController {

    @Autowired
    private PacienteService pacienteService;

    @GetMapping
    public ResponseEntity<List<Paciente>> getAllPacientes() {
        return ResponseEntity.ok(pacienteService.getAllPacientes());
    }

    @PostMapping
    public ResponseEntity<Paciente> savePaciente(@RequestBody Paciente paciente) {
        return ResponseEntity.ok(pacienteService.savePaciente(paciente));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Paciente> getPacienteById(@PathVariable Long id) {
        return ResponseEntity.ok(pacienteService.getPacienteById(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePaciente(@PathVariable Long id) {
        pacienteService.deletePaciente(id);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Paciente> actualizarPaciente(
            @PathVariable Long id,
            @RequestBody PacienteUpdateDTO pacienteDTO) {
        pacienteDTO.setId(id);
        return ResponseEntity.ok(pacienteService.updatePaciente(pacienteDTO));
    }
}
