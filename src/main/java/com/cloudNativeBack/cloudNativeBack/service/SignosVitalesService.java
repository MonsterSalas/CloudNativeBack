package com.cloudNativeBack.cloudNativeBack.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Service;

import com.cloudNativeBack.cloudNativeBack.exception.ResourceNotFoundException;
import com.cloudNativeBack.cloudNativeBack.model.SignosVitales;
import com.cloudNativeBack.cloudNativeBack.model.dto.SignosVitalesUpdateDTO;
import com.cloudNativeBack.cloudNativeBack.repository.PacienteRepository;
import com.cloudNativeBack.cloudNativeBack.repository.SignosVitalesRepository;

@Service
public class SignosVitalesService {
    private final SignosVitalesRepository signosVitalesRepository;
    private final PacienteRepository pacienteRepository;

    public SignosVitalesService(SignosVitalesRepository signosVitalesRepository,
            PacienteRepository pacienteRepository) {
        this.signosVitalesRepository = signosVitalesRepository;
        this.pacienteRepository = pacienteRepository;
    }

    public List<SignosVitales> getAllSignosVitales() {
        return signosVitalesRepository.findAll();
    }

    public SignosVitales crearSignosVitales(Long pacienteId, SignosVitales signosVitales) {
        return pacienteRepository.findById(pacienteId)
                .map(paciente -> {
                    signosVitales.setPaciente(paciente); // Asociar los signos vitales al paciente
                    signosVitales.setFechaRegistro(LocalDate.now());
                    return signosVitalesRepository.save(signosVitales);
                })
                .orElseThrow(() -> new ResourceNotFoundException("Paciente no encontrado con el ID: " + pacienteId));
    }

    public SignosVitales getSignosVitalesById(Long id) {
        return signosVitalesRepository.findById(id).orElse(null);
    }

    public void deleteSignosVitales(Long id) {
        signosVitalesRepository.deleteById(id);
    }

    public SignosVitales updateSignosVitales(Long id, SignosVitalesUpdateDTO dto) {
        return signosVitalesRepository.findById(id)
                .map(existente -> {
                    SignosVitales actualizado = SignosVitales.builder()
                            .id(id) // Usamos el ID del path parameter
                            .paciente(existente.getPaciente())
                            .fechaRegistro(dto.getFechaRegistro() != null ? dto.getFechaRegistro()
                                    : existente.getFechaRegistro())
                            .saturacionOxigeno(dto.getSaturacionOxigeno() != null ? dto.getSaturacionOxigeno()
                                    : existente.getSaturacionOxigeno())
                            .temperatura(
                                    dto.getTemperatura() != null ? dto.getTemperatura() : existente.getTemperatura())
                            .frecuenciaCardiaca(dto.getFrecuenciaCardiaca() != null ? dto.getFrecuenciaCardiaca()
                                    : existente.getFrecuenciaCardiaca())
                            .presionArterial(dto.getPresionArterial() != null ? dto.getPresionArterial()
                                    : existente.getPresionArterial())
                            .build();
                    return signosVitalesRepository.save(actualizado);
                })
                .orElseThrow(() -> new ResourceNotFoundException("No se encontraron signos vitales con el ID: " + id));
    }

    public List<SignosVitales> getSignosVitalesByPacienteId(Long pacienteId) {
        return signosVitalesRepository.findByPacienteId(pacienteId);
    }

}
