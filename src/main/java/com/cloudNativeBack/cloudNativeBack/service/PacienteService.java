package com.cloudNativeBack.cloudNativeBack.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.cloudNativeBack.cloudNativeBack.exception.ResourceNotFoundException;
import com.cloudNativeBack.cloudNativeBack.model.Paciente;
import com.cloudNativeBack.cloudNativeBack.model.dto.PacienteUpdateDTO;
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

    public Paciente updatePaciente(Long id, PacienteUpdateDTO dto) {
        return pacienteRepository.findById(id)
                .map(pacienteExistente -> {
                    Paciente pacienteActualizado = Paciente.builder()
                            .id(id) // Usamos el ID del path parameter
                            .nombre(dto.getNombre() != null ? dto.getNombre() : pacienteExistente.getNombre())
                            .apellido(dto.getApellido() != null ? dto.getApellido() : pacienteExistente.getApellido())
                            .rut(dto.getRut() != null ? dto.getRut() : pacienteExistente.getRut())
                            .edad(dto.getEdad() != null ? dto.getEdad() : pacienteExistente.getEdad())
                            .direccion(
                                    dto.getDireccion() != null ? dto.getDireccion() : pacienteExistente.getDireccion())
                            .fechaNacimiento(dto.getFechaNacimiento() != null ? dto.getFechaNacimiento()
                                    : pacienteExistente.getFechaNacimiento())
                            .estadoPaciente(dto.getEstadoPaciente() != null ? dto.getEstadoPaciente()
                                    : pacienteExistente.getEstadoPaciente())
                            .email(dto.getEmail() != null ? dto.getEmail() : pacienteExistente.getEmail())
                            .genero(dto.getGenero() != null ? dto.getGenero() : pacienteExistente.getGenero())
                            .telefono(dto.getTelefono() != null ? dto.getTelefono() : pacienteExistente.getTelefono())
                            .fechaIngreso(pacienteExistente.getFechaIngreso()) // Mantenemos la fecha original
                            .signosVitales(pacienteExistente.getSignosVitales()) // Mantenemos los signos vitales
                            .build();

                    return pacienteRepository.save(pacienteActualizado);
                })
                .orElseThrow(() -> new ResourceNotFoundException("No se encontró paciente con el ID: " + id));
    }
}
