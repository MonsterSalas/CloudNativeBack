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

    public Paciente updatePaciente(PacienteUpdateDTO pacienteDTO) {
        return pacienteRepository.findById(pacienteDTO.getId())
                .map(pacienteExistente -> {
                    // Usando @Builder.Default si quieres mantener valores por defecto
                    Paciente pacienteActualizado = Paciente.builder()
                            .id(pacienteExistente.getId())
                            .nombre(pacienteDTO.getNombre() != null ? pacienteDTO.getNombre()
                                    : pacienteExistente.getNombre())
                            .apellido(pacienteDTO.getApellido() != null ? pacienteDTO.getApellido()
                                    : pacienteExistente.getApellido())
                            .rut(pacienteDTO.getRut() != null ? pacienteDTO.getRut() : pacienteExistente.getRut())
                            .edad(pacienteDTO.getEdad() != null ? pacienteDTO.getEdad() : pacienteExistente.getEdad())
                            .direccion(pacienteDTO.getDireccion() != null ? pacienteDTO.getDireccion()
                                    : pacienteExistente.getDireccion())
                            .fechaNacimiento(pacienteDTO.getFechaNacimiento() != null ? pacienteDTO.getFechaNacimiento()
                                    : pacienteExistente.getFechaNacimiento())
                            .estadoPaciente(pacienteDTO.getEstadoPaciente() != null ? pacienteDTO.getEstadoPaciente()
                                    : pacienteExistente.getEstadoPaciente())
                            .email(pacienteDTO.getEmail() != null ? pacienteDTO.getEmail()
                                    : pacienteExistente.getEmail())
                            .genero(pacienteDTO.getGenero() != null ? pacienteDTO.getGenero()
                                    : pacienteExistente.getGenero())
                            .telefono(pacienteDTO.getTelefono() != null ? pacienteDTO.getTelefono()
                                    : pacienteExistente.getTelefono())
                            .fechaIngreso(pacienteExistente.getFechaIngreso()) // Mantenemos la fecha original
                            .signosVitales(pacienteExistente.getSignosVitales()) // Mantenemos los signos vitales
                            .build();

                    return pacienteRepository.save(pacienteActualizado);
                })
                .orElseThrow(() -> new ResourceNotFoundException(
                        "No se encontró paciente con el ID: " + pacienteDTO.getId()));
    }

}
