package com.cloudNativeBack.cloudNativeBack.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.cloudNativeBack.cloudNativeBack.exception.ResourceNotFoundException;
import com.cloudNativeBack.cloudNativeBack.model.Paciente;
import com.cloudNativeBack.cloudNativeBack.repository.PacienteRepository;
@Service
public class PacienteService {

    private final PacienteRepository pacienteRepository;

    public PacienteService(PacienteRepository pacienteRepository) {
        this.pacienteRepository = pacienteRepository;
    }

    public List<Paciente> getAllPacientes() {
        return pacienteRepository.findAll();
    }

    public Paciente savePaciente(Paciente paciente) {
        return pacienteRepository.save(paciente);
    }

    public Paciente getPacienteById(Long id) {
        return pacienteRepository.findById(id).orElse(null);
    }

    public void deletePaciente(Long id) {
        pacienteRepository.deleteById(id);
    }

public Paciente updatePaciente(Paciente pacienteNuevo) {
    return pacienteRepository.findById(pacienteNuevo.getId())
        .map(pacienteExistente -> {
            // Mantener valores existentes si los nuevos son null
            if (pacienteNuevo.getNombre() == null) {
                pacienteNuevo.setNombre(pacienteExistente.getNombre());
            }
            if (pacienteNuevo.getApellido() == null) {
                pacienteNuevo.setApellido(pacienteExistente.getApellido());
            }
            if (pacienteNuevo.getRut() == null) {
                pacienteNuevo.setRut(pacienteExistente.getRut());
            }
            if (pacienteNuevo.getEdad() == null) {
                pacienteNuevo.setEdad(pacienteExistente.getEdad());
            }
            if (pacienteNuevo.getDireccion() == null) {
                pacienteNuevo.setDireccion(pacienteExistente.getDireccion());
            }
            if (pacienteNuevo.getFechaNacimiento() == null) {
                pacienteNuevo.setFechaNacimiento(pacienteExistente.getFechaNacimiento());
            }
            if (pacienteNuevo.getEstadoPaciente() == null) {
                pacienteNuevo.setEstadoPaciente(pacienteExistente.getEstadoPaciente());
            }
            if (pacienteNuevo.getEmail() == null) {
                pacienteNuevo.setEmail(pacienteExistente.getEmail());
            }
            if (pacienteNuevo.getGenero() == null) {
                pacienteNuevo.setGenero(pacienteExistente.getGenero());
            }
            if (pacienteNuevo.getTelefono() == null) {
                pacienteNuevo.setTelefono(pacienteExistente.getTelefono());
            }
            
            // Mantener la fecha de ingreso original
            pacienteNuevo.setFechaIngreso(pacienteExistente.getFechaIngreso());
            
            // Mantener la lista de signos vitales
            pacienteNuevo.setSignosVitales(pacienteExistente.getSignosVitales());
            
            return pacienteRepository.save(pacienteNuevo);
        })
        .orElseThrow(() -> new ResourceNotFoundException("No se encontró paciente con el ID: " + pacienteNuevo.getId()));
}

}
