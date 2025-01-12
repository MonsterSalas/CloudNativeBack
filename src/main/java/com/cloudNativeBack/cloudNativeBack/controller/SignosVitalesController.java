package com.cloudNativeBack.cloudNativeBack.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;

import com.cloudNativeBack.cloudNativeBack.model.SignosVitales;
import com.cloudNativeBack.cloudNativeBack.service.SignosVitalesService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
@RestController
@CrossOrigin
@RequestMapping("/back/signosvitales")
public class SignosVitalesController {

    @Autowired
    private SignosVitalesService signosVitalesService;

    @GetMapping
    public ResponseEntity<List<SignosVitales>> getAllSignosVitales() {
        return ResponseEntity.ok(signosVitalesService.getAllSignosVitales());
    }

    @PostMapping("/{pacienteId}")
    public ResponseEntity<SignosVitales> crearSignosVitales(@PathVariable Long pacienteId, @RequestBody SignosVitales signosVitales) {
        return ResponseEntity.ok(signosVitalesService.crearSignosVitales(pacienteId, signosVitales));
    }

    @GetMapping("/{id}")
    public ResponseEntity<SignosVitales> getSignosVitalesById(@PathVariable Long id) {
        return ResponseEntity.ok(signosVitalesService.getSignosVitalesById(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSignosVitales(@PathVariable Long id) {
        signosVitalesService.deleteSignosVitales(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping
    public ResponseEntity<SignosVitales> updateSignosVitales(@RequestBody SignosVitales signosVitales) {
        return ResponseEntity.ok(signosVitalesService.updateSignosVitales(signosVitales));
    }

    @GetMapping("/paciente/{pacienteId}")
    public ResponseEntity<List<SignosVitales>> getSignosVitalesByPacienteId(@PathVariable Long pacienteId) {
        return ResponseEntity.ok(signosVitalesService.getSignosVitalesByPacienteId(pacienteId));
    }



}
