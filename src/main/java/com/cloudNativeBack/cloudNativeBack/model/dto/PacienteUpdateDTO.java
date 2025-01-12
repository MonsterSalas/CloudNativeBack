package com.cloudNativeBack.cloudNativeBack.model.dto;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data  // Genera getters, setters, equals, hashCode y toString
@AllArgsConstructor
@NoArgsConstructor
public class PacienteUpdateDTO {
    private Long id;
    private String nombre;
    private String apellido;
    private String rut;
    private Integer edad;
    private String direccion;
    private LocalDate fechaNacimiento;
    private String estadoPaciente;
    private String email;
    private String genero;
    private String telefono;
}