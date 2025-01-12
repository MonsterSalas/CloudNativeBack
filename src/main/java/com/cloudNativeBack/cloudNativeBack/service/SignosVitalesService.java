package com.cloudNativeBack.cloudNativeBack.service;
import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Service;

import com.cloudNativeBack.cloudNativeBack.exception.ResourceNotFoundException;
import com.cloudNativeBack.cloudNativeBack.model.SignosVitales;
import com.cloudNativeBack.cloudNativeBack.repository.PacienteRepository;
import com.cloudNativeBack.cloudNativeBack.repository.SignosVitalesRepository;
@Service
public class SignosVitalesService {
    private final SignosVitalesRepository signosVitalesRepository;
    private final PacienteRepository pacienteRepository;
    public SignosVitalesService(SignosVitalesRepository signosVitalesRepository, PacienteRepository pacienteRepository) {
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
    public SignosVitales updateSignosVitales(SignosVitales signosVitalesNuevo) {
        return signosVitalesRepository.findById(signosVitalesNuevo.getId())
            .map(signosVitalesExistente -> {
                // Mantener la referencia al paciente
                signosVitalesNuevo.setPaciente(signosVitalesExistente.getPaciente());
                
                // Mantener valores existentes si los nuevos son null
                if (signosVitalesNuevo.getFechaRegistro() == null) {
                    signosVitalesNuevo.setFechaRegistro(signosVitalesExistente.getFechaRegistro());
                }
                if (signosVitalesNuevo.getTemperatura() == null) {
                    signosVitalesNuevo.setTemperatura(signosVitalesExistente.getTemperatura());
                }
                if (signosVitalesNuevo.getFrecuenciaCardiaca() == null) {
                    signosVitalesNuevo.setFrecuenciaCardiaca(signosVitalesExistente.getFrecuenciaCardiaca());
                }
                if (signosVitalesNuevo.getPresionArterial() == null) {
                    signosVitalesNuevo.setPresionArterial(signosVitalesExistente.getPresionArterial());
                }
                
                return signosVitalesRepository.save(signosVitalesNuevo);
            })
            .orElseThrow(() -> new ResourceNotFoundException("No se encontraron signos vitales con el ID: " + signosVitalesNuevo.getId()));
    }
    public List<SignosVitales> getSignosVitalesByPacienteId(Long pacienteId) {
        return signosVitalesRepository.findByPacienteId(pacienteId);
    }

}
